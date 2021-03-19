import java.io.IOException;
import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import org.w3c.dom.Node;
import org.w3c.dom.Document;

public class rewriter {
    String rewrite = "";
    HashMap<Integer, ArrayList<String>> whereHash = new HashMap<>();
    HashMap<String, String> forHash = new HashMap<>();
    HashMap<Integer, ArrayList<String>> forSet = new HashMap<>();
    String[][] listcond;

    public boolean Needrewrite(XqueryParser.Xq_flwerContext ctx){
        if(ctx.whereClause() == null){
            return false;
        }
        else{
            if(ctx.whereClause().getText().contains("eq") || ctx.whereClause().getText().contains("and") || ctx.whereClause().getText().contains("=") ){
                return true;
            }
            else{
                return false;
            }
        }
    }
    public String rewritr(XqueryParser.Xq_flwerContext ctx){
        rewrite += "for $tuple in ";
        int rootnum=0;
        //for clause
        for(int i=0; i<ctx.forClause().var().size(); i++){
            String var = ctx.forClause().var(i).getText();
            String xq = ctx.forClause().xq(i).getText();
            if(xq.contains("doc")){
                if(!forHash.containsKey("root")){
                    forHash.put("root", var+"in"+xq);
                }
                else{
                    String roots = forHash.get("root") + "," + var + "in" + xq;
                    forHash.put("root", roots);
                }
                rootnum++;
            }
            else{
                String key = xq.split("/")[0];
                if(!forHash.containsKey(key)){
                    forHash.put(key,var + "in" + xq);
                }
                else{
                    String vars = forHash.get(key) + "," + var + "in" + xq;
                    forHash.put(key,vars);
                }
            }
        }

        if(rootnum == 1){
            return "";
        }

        //combine tuple sets
        String[] roots = forHash.get("root").split(",");
        for(int i=0; i<rootnum; i++){
            String root = roots[i];
            ArrayList<String> temp = new ArrayList<>();
            temp.add(root);
            String key = root.split("in")[0];
            temp.addAll(addAllkey(key));
            forSet.put(i, temp);
        }

        //where clause
        String where = ctx.whereClause().getText().split("where")[1];
        String[] conds = where.split("and");
        listcond = new String[rootnum][rootnum];
        for(int i=0; i<conds.length; i++){
            String cond = conds[i];
            String leftvar = cond.split("eq|=")[0];
            String rightvar = cond.split("eq|=")[1];
            int left= rootnum;
            int right= rootnum;
            for(int j=0; j<rootnum; j++){
                for(int k=0; k<forSet.get(j).size(); k++) {
                    if (forSet.get(j).get(k).split("in")[0].equals(leftvar)) {
                        left = j;
                    }
                    if (forSet.get(j).get(k).split("in")[0].equals(rightvar)) {
                        right = j;
                    }
                }
            }
            if(left == right && left == rootnum){
                if(whereHash.containsKey(0)){
                    whereHash.get(0).add(cond);
                }
                else{
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(cond);
                    whereHash.put(0, temp);
                }
            }
            else if(left == right && left != rootnum){
                if(whereHash.containsKey(left)){
                    whereHash.get(left).add(cond);
                }
                else{
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(cond);
                    whereHash.put(left, temp);
                }
            }
            else if(left != right && left != rootnum && right != rootnum){
                if(listcond[left][right] == null){
                    listcond[left][right] = cond;
                }
                else{
                    listcond[left][right] += ",";
                    listcond[left][right] += cond;
                }
            }
            else if(left != right && left == rootnum && right != rootnum){
                if(whereHash.containsKey(right)){
                    whereHash.get(right).add(cond);
                }
                else{
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(cond);
                    whereHash.put(right, temp);
                }
            }
            else if(left != right && left != rootnum && right == rootnum){
                if(whereHash.containsKey(left)){
                    whereHash.get(left).add(cond);
                }
                else{
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(cond);
                    whereHash.put(left, temp);
                }
            }
        }

        //System.out.println(Arrays.deepToString(listcond));
        rewrite += combine(rootnum - 1, rootnum);

        //return clause
        rewrite += "return ";
        String[] returnParen = ctx.returnClause().getText().split("return")[1].split("\\{");
        rewrite += returnParen[0];
        StringBuilder returnrewrite = new StringBuilder();
        for(int j=1; j< returnParen.length; j++){
            String xq = returnParen[j].split("}", 2)[0];
            if(returnParen[j].contains("}")){
                String Varname = returnParen[j].split("}")[1];
                String[] vars = xq.split(",");
                returnrewrite.append("{ ");
                StringBuilder temp = new StringBuilder();
                for(int m=0; m< vars.length; m++){
                    temp.append("$tuple/").append(vars[m].split("/")[0].split("\\$")[1]).append("/*");
                    if(vars[m].contains("/")) {
                        if (!vars[m].split("/", 2)[1].equals("")) {
                            temp.append("/").append(vars[m].split("/", 2)[1]);
                        }
                    }
                    if(m!=vars.length-1){
                        temp.append(" ,");
                    }
                }
                returnrewrite.append(temp).append(" }").append(Varname);
            }
            else{
                returnrewrite.append("{ ");
                StringBuilder temp = new StringBuilder();
                if(xq.contains("$")){
                    String[] vars = xq.split(",");
                    for(int m=0; m< vars.length; m++){
                        if(vars[m].contains("$")) {
                            temp.append("$tuple/").append(vars[m].split("/")[0].split("\\$")[1]).append("/*");
                            if(vars[m].contains("/")) {
                                if (!vars[m].split("/", 2)[1].equals("")) {
                                    //System.out.println(vars[m].split("/", 2)[1]);
                                    temp.append("/").append(vars[m].split("/", 2)[1]);
                                }
                            }
                        }
                        else{
                            temp.append(vars[m]);
                        }
                        if(m!=vars.length-1){
                            temp.append(" ,");
                        }
                    }
                    returnrewrite.append(temp);
                }
                else{
                    returnrewrite.append(xq);
                }
            }
            if(j == returnParen.length - 1){
                String last[] = returnParen[j].split("}");
                for(int m=2; m<last.length; m++){
                    returnrewrite.append(" }").append(last[m]);
                }
            }
        }
        rewrite += returnrewrite.toString();
        return rewrite;
    }

    public ArrayList<String> addAllkey(String key){
        ArrayList<String> temp = new ArrayList<>();
        if(forHash.containsKey(key)){
            String[] vars = forHash.get(key).split(",");
            for(int j=0; j<vars.length; j++){
                temp.add(vars[j]);
                String varKey = vars[j].split("in")[0];
                temp.addAll(addAllkey(varKey));
            }
        }
        return temp;
    }

    public String combine(int i, int rootnum){
        if(i>1) {
            String temp = "join ( ";
            temp += combine(i-1, rootnum);
            temp += ", ";
            StringBuilder ret = new StringBuilder();
            //for clause
            ret.append("for ");
            for (int j = 0; j < forSet.get(i).size(); j++) {
                ret.append(forSet.get(i).get(j).split("in")[0]).append(" in ").append(forSet.get(i).get(j).split("in")[1]);
                if (j != forSet.get(i).size()-1) {
                    ret.append(" , ");
                }
            }

            //where clause
            if(whereHash.containsKey(i)) {
                ret.append(" where ");
                for (int j = 0; j < whereHash.get(i).size(); j++) {
                    ret.append(whereHash.get(i).get(j).split("eq|=")[0]);
                    if(whereHash.get(i).get(j).contains("eq")){
                        ret.append(" eq ").append(whereHash.get(i).get(j).split("eq|=")[1]);
                    }
                    else{
                        ret.append(" = ").append(whereHash.get(i).get(j).split("eq|=")[1]);
                    }
                    if (j != whereHash.get(i).size() - 1) {
                        ret.append(" and ");
                    }
                }
            }

            //return clause
            ret.append(" return <tuple> { ");
            for (int j = 0; j < forSet.get(i).size(); j++) {
                String var = forSet.get(i).get(j).split("in")[0];
                String varname = var.replace("$", "");
                ret.append("<").append(varname).append("> {").append(var).append("} </").append(varname).append(">");
                if (j != forSet.get(i).size()-1) {
                    ret.append(" , ");
                }
            }
            ret.append(" } </tuple>, ");
            StringBuilder leftlist = new StringBuilder();
            StringBuilder rightlist = new StringBuilder();
            leftlist.append("[");
            rightlist.append("[");
            for(int m=0; m<i; m++) {
                if (listcond[m][i] != null) {
                    if(m>0){
                        if(listcond[m-1][i] != null){
                            leftlist.append(", ");
                            rightlist.append(", ");
                        }
                    }
                    String[] conds = listcond[m][i].split(",");
                    for (int j = 0; j < conds.length; j++) {
                        String leftvar = conds[j].split("eq|=")[0];
                        String rightvar = conds[j].split("eq|=")[1];
                        boolean swap = false;
                        for(int k=0; k< forSet.size(); k++) {
                            for (int n = 0; n < forSet.get(k).size(); n++) {
                                String varKey = forSet.get(k).get(n).split("in")[0];
                                if(varKey.equals(rightvar) && k==m){
                                    swap = true;
                                }
                            }
                        }
                        if(swap){
                            leftlist.append(rightvar.split("\\$")[1]);
                            rightlist.append(leftvar.split("\\$")[1]);
                        }
                        else{
                            leftlist.append(leftvar.split("\\$")[1]);
                            rightlist.append(rightvar.split("\\$")[1]);
                        }
                        if (j != conds.length - 1) {
                            leftlist.append(" , ");
                            rightlist.append(" , ");
                        }
                    }
                }
                if (listcond[i][m] != null) {
                    if(listcond[m][i]!=null) {
                        leftlist.append(", ");
                        rightlist.append(", ");
                    }
                    String[] conds = listcond[i][m].split(",");
                    for (int j = 0; j < conds.length; j++) {
                        String leftvar = conds[j].split("eq|=")[0];
                        String rightvar = conds[j].split("eq|=")[1];
                        boolean swap = false;
                        for(int k=0; k< forSet.size(); k++) {
                            for (int n = 0; n < forSet.get(k).size(); n++) {
                                String varKey = forSet.get(k).get(n).split("in")[0];
                                if(varKey.equals(rightvar) && k==i){
                                    swap = true;
                                }
                            }
                        }
                        if(swap){
                            leftlist.append(rightvar.split("\\$")[1]);
                            rightlist.append(leftvar.split("\\$")[1]);
                        }
                        else{
                            leftlist.append(leftvar.split("\\$")[1]);
                            rightlist.append(rightvar.split("\\$")[1]);
                        }
                        if (j != conds.length - 1) {
                            leftlist.append(" , ");
                            rightlist.append(" , ");
                        }
                    }
                }
            }
            leftlist.append("]");
            rightlist.append("]");
            ret.append(leftlist).append(" , ").append(rightlist).append(" ) ");
            temp+=ret.toString();
            return temp;
        }
        else{
            StringBuilder ret = new StringBuilder();
            ret.append("join ( ");
            for(int h=0; h<2; h++) {
                //for clause
                ret.append("for ");
                for (int j = 0; j < forSet.get(h).size(); j++) {
                    ret.append(forSet.get(h).get(j).split("in")[0]).append(" in ").append(forSet.get(h).get(j).split("in")[1]);
                    if (j != forSet.get(h).size()-1) {
                        ret.append(" , ");
                    }
                }

                //where clause
                if(whereHash.containsKey(h)) {
                    ret.append(" where ");
                    for (int j = 0; j < whereHash.get(h).size(); j++) {
                        ret.append(whereHash.get(h).get(j).split("eq|=")[0]);
                        if(whereHash.get(h).get(j).contains("eq")){
                            ret.append(" eq ").append(whereHash.get(h).get(j).split("eq|=")[1]);
                        }
                        else{
                            ret.append(" = ").append(whereHash.get(h).get(j).split("eq|=")[1]);
                        }
                        if (j != whereHash.get(h).size() - 1) {
                            ret.append(" and ");
                        }
                    }
                }

                //return clause
                ret.append(" return <tuple>{ ");
                for (int j = 0; j < forSet.get(h).size(); j++) {
                    String var = forSet.get(h).get(j).split("in")[0];
                    String varname = var.replace("$", "");
                    ret.append("<").append(varname).append("> {").append(var).append("} </").append(varname).append(">");
                    if (j != forSet.get(h).size()-1) {
                        ret.append(" , ");
                    }
                }
                ret.append(" }</tuple>, ");
            }
            StringBuilder leftlist = new StringBuilder();
            StringBuilder rightlist = new StringBuilder();
            leftlist.append("[");
            rightlist.append("[");
            if(listcond[0][1]!=null) {
                String[] conds = listcond[0][1].split(",");
                for(int j=0; j< conds.length; j++){
                    String leftvar = conds[j].split("eq|=")[0];
                    String rightvar = conds[j].split("eq|=")[1];
                    boolean swap = false;
                    for(int m=0; m< forSet.size(); m++) {
                        for (int n = 0; n < forSet.get(m).size(); n++) {
                            String varKey = forSet.get(m).get(n).split("in")[0];
                            if(varKey.equals(rightvar) && m==0){
                                swap = true;
                            }
                        }
                    }
                    if(swap){
                        leftlist.append(rightvar.split("\\$")[1]);
                        rightlist.append(leftvar.split("\\$")[1]);
                    }
                    else{
                        leftlist.append(leftvar.split("\\$")[1]);
                        rightlist.append(rightvar.split("\\$")[1]);
                    }
                    if(j != conds.length-1){
                        leftlist.append(",");
                        rightlist.append(",");
                    }
                }
            }
            if(listcond[1][0]!=null) {
                if(listcond[0][1]!=null) {
                    leftlist.append(",");
                    rightlist.append(",");
                }
                String[] conds = listcond[1][0].split(",");
                for(int j=0; j< conds.length; j++){
                    String leftvar = conds[j].split("eq|=")[0];
                    String rightvar = conds[j].split("eq|=")[1];
                    boolean swap = false;
                    for(int m=0; m< forSet.size(); m++) {
                        for (int n = 0; n < forSet.get(m).size(); n++) {
                            String varKey = forSet.get(m).get(n).split("in")[0];
                            if(varKey.equals(rightvar) && m==0){
                                swap = true;
                            }
                        }
                    }
                    if(swap){
                        leftlist.append(rightvar.split("\\$")[1]);
                        rightlist.append(leftvar.split("\\$")[1]);
                    }
                    else{
                        leftlist.append(leftvar.split("\\$")[1]);
                        rightlist.append(rightvar.split("\\$")[1]);
                    }
                    if(j != conds.length-1){
                        leftlist.append(",");
                        rightlist.append(",");
                    }
                }
            }
            leftlist.append("]");
            rightlist.append("]");
            ret.append(leftlist).append(" ,").append(rightlist).append(" )");
            return ret.toString();
        }
    }
}
