// import java.io.IOException;
// import java.io.File;
// import java.io.OutputStreamWriter;
// import java.io.PrintWriter;
// import java.lang.reflect.Array;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Iterator;
// import java.util.HashSet;
import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.*;

import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Entity;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class XqVisitor extends XqueryBaseVisitor<ArrayList<Node>>{
    ArrayList<Node> result = new ArrayList<>();
    ArrayList<Node> tp = new ArrayList<>();

    HashMap<String, ArrayList<Node>> varHash = new HashMap<>();

    Document doc = null;
    Document output = null;
    boolean rm = false;
    boolean all = false;
    boolean init = false;
    boolean rewrited = false;
    rewriter rewriter = new rewriter();
    int test=0;

//join -----------------------------------------------------------------------------------------------------------------

    private ArrayList<Node> getChildren(Node n) {
        ArrayList<Node> children = new ArrayList<Node>();
        for (int i = 0; i < n.getChildNodes().getLength(); i++) {
            children.add(n.getChildNodes().item(i));
        }
        return children;
    }

    private void printNodes (ArrayList<Node> nodes) {
        for (Node n : nodes) {
            StringWriter sw = new StringWriter();
            try {
                Transformer t = TransformerFactory.newInstance().newTransformer();
                t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                t.setOutputProperty(OutputKeys.INDENT, "yes");
                t.transform(new DOMSource(n), new StreamResult(sw));
                System.out.println(sw.toString());
            } catch (TransformerException te) {
                System.out.println("nodeToString Transformer Exception");
            }
        }
    }

    @Override
    public ArrayList<Node> visitXq_join(XqueryParser.Xq_joinContext ctx) {
// System.out.println("in join");
        //visit left and right xq
        ArrayList<Node> prev = result;
        test = 1;
        ArrayList<Node> left = visit(ctx.xq(0));
        // System.out.println("=========left============");
        // System.out.println(ctx.xq(0).getText());
        // System.out.println("=========left============");
        // printNodes(left);
        test = 2;
        result = prev;
        ArrayList<Node> right = visit(ctx.xq(1));
        // System.out.println("=========right============");
        // System.out.println(ctx.xq(1).getText());
        // System.out.println("=========right============");
        // printNodes(right);
        // System.out.println("right size : " + right.size());



        //visit left and right list
        ArrayList<String> leftKey = new ArrayList<String>();
        ArrayList<String> rightKey = new ArrayList<String>();

        for (int i = 0; i < ctx.list(0).VARNAME().size(); i++) {
            leftKey.add(ctx.list(0).VARNAME(i).getText());
            rightKey.add(ctx.list(1).VARNAME(i).getText());
        }

        /*System.out.println("test");
        System.out.println(left);
        System.out.println(right);
        System.out.println(leftKey.get(0));
        System.out.println(rightKey.get(0));*/

        //generate hashmap with left
        HashMap<String, ArrayList<Node>> leftHash= new HashMap<>();
        for (int j = 0; j < left.size(); j++) {
            String key = "";
            ArrayList<Node> currChildren = getChildren(left.get(j));
            // printNodes(currChildren);
            for (String s : leftKey) {
                // System.out.println("s : " + s);
                for (int k = 0; k < currChildren.size(); k++) {
                    // System.out.println("currChildren : " + currChildren.get(k).getNodeName());
                    if (currChildren.get(k).getNodeName().equals(s)) {
                        key += "@" + currChildren.get(k).getFirstChild().getTextContent();
                    }
                }
            }
            if(leftKey.size() == 1 && leftKey.get(0).equals("<missing VARNAME>")){
                if (leftHash.containsKey("ALongNameThatCouldAvoidRepeat")) {
                    leftHash.get("ALongNameThatCouldAvoidRepeat").add(left.get(j));
                }
                else{
                    ArrayList<Node> val = new ArrayList<Node>();
                    val.add(left.get(j));
                    leftHash.put("ALongNameThatCouldAvoidRepeat", val);
                }
            }
            if (leftHash.containsKey(key)) {
                leftHash.get(key).add(left.get(j));
            }
            else if (key.length() != 0){
                ArrayList<Node> val = new ArrayList<Node>();
                val.add(left.get(j));
                leftHash.put(key, val);
            }
        }
        // for (String s : leftHash.keySet()) {
        //     System.out.println("key : " + s + "; value : " + leftHash.get(s).size());
        //     printNodes(leftHash.get(s));
        // }

        ArrayList<Node> joinResult = new ArrayList<Node>();
        for (int p = 0; p < right.size(); p++) {
            String key = "";
            ArrayList<Node> currChildren = getChildren(right.get(p));
            for (String s : rightKey) {
                for (int q = 0; q < currChildren.size(); q++) {
                    if (currChildren.get(q).getNodeName().equals(s)) {
                        key += "@" + currChildren.get(q).getFirstChild().getTextContent();
                    }
                }
            }
            if(leftHash.containsKey("ALongNameThatCouldAvoidRepeat")){
                for (Node n : leftHash.get("ALongNameThatCouldAvoidRepeat")) {
                    try {
                        if (!init) {
                            DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
                            DocumentBuilder db1 = dbf1.newDocumentBuilder();
                            output = db1.newDocument();
                            init = true;
                        }
                    }
                    catch(Exception e){

                    }
                    //join
                    ArrayList<Node> leftChildren = getChildren(n);
                    leftChildren.addAll(currChildren);
                    Node tuple = output.createElement("tuple");
                    for(Node r : leftChildren){
                        if(r != null) {
                            Node child = output.importNode(r, true);
                            tuple.appendChild(child);
                        }
                    }
                    joinResult.add(tuple);
                }
            }
            if (leftHash.containsKey(key)) {
                for (Node n : leftHash.get(key)) {
                    try {
                        if (!init) {
                            DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
                            DocumentBuilder db1 = dbf1.newDocumentBuilder();
                            output = db1.newDocument();
                            init = true;
                        }
                    }
                    catch(Exception e){

                    }
                    //join
                    ArrayList<Node> leftChildren = getChildren(n);
                    leftChildren.addAll(currChildren);
                    Node tuple = output.createElement("tuple");
                    for(Node r : leftChildren){
                        if(r != null) {
                            Node child = output.importNode(r, true);
                            tuple.appendChild(child);
                        }
                    }
                    joinResult.add(tuple);
                }
            }
        }
        //System.out.println("result size : " + joinResult.size() + "result : ");
        // printNodes(joinResult);
        result = joinResult;
        return joinResult;
    }


//xq -------------------------------------------------------------------------------------------------------------------

    //done
    @Override
    public ArrayList<Node> visitXq_variable(XqueryParser.Xq_variableContext ctx){
        ArrayList<Node> temp = new ArrayList<>();
        temp.addAll(varHash.get(ctx.var().getText()));
        result = temp;
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitXq_Stringconstant(XqueryParser.Xq_StringconstantContext ctx){
        String content = ctx.Stringconstant().getText();
        String ctt = content.substring(1, content.length()-1);
        Node cttNode = doc.createTextNode(ctt);
        ArrayList<Node> temp = new ArrayList<>();
        temp.add(cttNode);
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitXq_ap(XqueryParser.Xq_apContext ctx){
        return visit(ctx.ap());
    }

    //done
    @Override
    public ArrayList<Node> visitXq_self(XqueryParser.Xq_selfContext ctx){
        return visit(ctx.xq());
    }

    //done
    @Override
    public ArrayList<Node> visitXq_merge(XqueryParser.Xq_mergeContext ctx){
        // System.out.println("in xq merge : " + ctx.xq(0).getText() + " ; " + ctx.xq(1).getText());
        ArrayList<Node> temp = result;
        ArrayList<Node> left = visit(ctx.xq(0));
        // System.out.println("left resut : ");
        // printNodes(left);
        result = temp;
        ArrayList<Node> right = visit(ctx.xq(1));
        // System.out.println("right resut : ");
        // printNodes(right);
        left.addAll(right);
        result = left;
        return left;
    }

    //done
    @Override
    public ArrayList<Node> visitXq_rpchildren(XqueryParser.Xq_rpchildrenContext ctx){
        // System.out.println("!!!!!!!!!!!!@@@@@@@@@@!!!!!!!!!!!!!!!!");
        // System.out.println("in rpchildren : " + ctx.xq().getText() + " ; " + ctx.rp().getText());
        result = visit(ctx.xq());
        rm = true;
        return visit(ctx.rp());
    }

    //done
    @Override
    public ArrayList<Node> visitXq_rpall(XqueryParser.Xq_rpallContext ctx){
        result = visit(ctx.xq());
        rm = true;
        all = true;
        return visit(ctx.rp());
    }

    //done
    @Override
    public ArrayList<Node> visitXq_tag(XqueryParser.Xq_tagContext ctx) {
        try {
            if (!init) {
                DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
                DocumentBuilder db1 = dbf1.newDocumentBuilder();
                output = db1.newDocument();
                init = true;
            }
        }
        catch(Exception e){

        }
        ArrayList<Node> temp = new ArrayList<>();
        // printNodes(result);
        ArrayList<Node> ret = visit(ctx.xq());
        // printNodes(ret);
        Node create = output.createElement(ctx.VARNAME(0).getText());
        for(Node r:ret){
            if(r!=null) {
                Node child = output.importNode(r, true);
                // System.out.println(child);
                create.appendChild(child);
            }
        }
        temp.add(create);
        return temp;
    }

    @Override
    public ArrayList<Node> visitXq_flwer(XqueryParser.Xq_flwerContext ctx){
// System.out.println("in flwer");

        if(rewriter.Needrewrite(ctx) && rewrited == false){
            String reString = rewriter.rewritr(ctx);
            if(reString == ""){
                ArrayList<Node> prevResult = result;
                ArrayList<Node> curr = flwerHelper(0, ctx);
                result = prevResult;
                return curr;
            }
            else{
                System.out.println("---------------------");
                System.out.println("REWRITTEN QUERY : " + reString);
                System.out.println("---------------------");
                ANTLRInputStream antlrIS = new ANTLRInputStream(reString);
                XqueryLexer xqLexer = new XqueryLexer(antlrIS);
                CommonTokenStream commonTS = new CommonTokenStream(xqLexer);
                XqueryParser xqParser = new XqueryParser(commonTS);
                ParseTree pTree = xqParser.xq();
                XqVisitor visitor = new XqVisitor();
                visitor.rewrited = true;
                ArrayList<Node> reresult = visitor.visit(pTree);
                //ArrayList<Node> reresult = new ArrayList<>();
                return reresult;
            }
        }
        else {
            ArrayList<Node> prevResult = result;
            ArrayList<Node> curr = flwerHelper(0, ctx);
            result = prevResult;
            return curr;
        }
    }

    private ArrayList<Node> flwerHelper(int k, XqueryParser.Xq_flwerContext ctx) {
        //not rewriting
        if (k == ctx.forClause().var().size()) {
            if (ctx.letClause() != null) {
                visit(ctx.letClause());
            }
            if (ctx.whereClause() != null) {
                ArrayList<Node> sats = visit(ctx.whereClause());
                if (!sats.isEmpty()) {
                    return visit(ctx.returnClause());
                }
            }
            else {
                return visit(ctx.returnClause());
            }
            return new ArrayList<Node>();
        }
        else {
            ArrayList<Node> allResults = new ArrayList<Node>();
            ArrayList<Node> xqResult = visit(ctx.forClause().xq(k));
            ArrayList<Node> prevResult = result;
            for (int i = 0; i < xqResult.size(); i++) {
                ArrayList<Node> tmp = new ArrayList<Node>();
                tmp.add(xqResult.get(i));
                result = tmp;
                varHash.put(ctx.forClause().var(k).getText(), tmp);
                allResults.addAll(flwerHelper(k + 1, ctx));
            }
            result = prevResult;
            return allResults;
        }
    }


    //done
    @Override
    public ArrayList<Node> visitXq_let(XqueryParser.Xq_letContext ctx){
        ArrayList<Node> temp = new ArrayList<>();
        result = visit(ctx.letClause());
        temp = visit(ctx.xq());
        result = temp;
        return temp;
    }

//end of xq-------------------------------------------------------------------------------------------------------------

    //cond------------------------------------------------------------------------------------------------------------------
    //done
    @Override
    public ArrayList<Node> visitCond_equal(XqueryParser.Cond_equalContext ctx){
        ArrayList<Node> temp = new ArrayList<>();
        tp = result;
        ArrayList<Node> left = visit(ctx.xq(0));
        result = tp;
        ArrayList<Node> right = visit(ctx.xq(1));

        for (Node l : left) {
            for (Node s : right) {
                if (l.isEqualNode(s)) {
                    temp.add(l);
                    return temp;
                }
            }
        }
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitCond_is(XqueryParser.Cond_isContext ctx){
        ArrayList<Node> temp = new ArrayList<>();
        tp = result;
        ArrayList<Node> left = visit(ctx.xq(0));
        result = tp;
        ArrayList<Node> right = visit(ctx.xq(1));
        for (Node l : left) {
            for (Node s : right) {
                if (l.isSameNode(s)) {
                    temp.add(l);
                }
            }
        }
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitCond_empty(XqueryParser.Cond_emptyContext ctx){
        ArrayList<Node> temp = new ArrayList<>();
        tp = result;
        ArrayList<Node> ret = visit(ctx.xq());
        if(ret.isEmpty()){
            temp.add(doc.createElement("empty"));
        }
        result = tp;
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitCond_satisfy(XqueryParser.Cond_satisfyContext ctx){
        ArrayList<Node> prevResult = result;
        ArrayList<Node> tmp = satHelper(0, ctx);
        result = prevResult;
        return tmp;
    }

    private ArrayList<Node> satHelper (int k, XqueryParser.Cond_satisfyContext ctx) {
        ArrayList<Node> prevResult = result;

        if (k == ctx.var().size()) {
            return visit(ctx.cond());
        }

        else {
            prevResult = result;
            ArrayList<Node> ret = visit(ctx.xq(k));
            ArrayList<Node> tmp = new ArrayList<>();
            for (Node n : ret) {
                ArrayList<Node> tmpAL = new ArrayList<Node>();
                tmpAL.add(n);
                result = tmpAL;
                varHash.put(ctx.var(k).getText(), tmpAL);
                tmp.addAll(satHelper(k+1, ctx));
            }
            result = prevResult;
            return tmp;
        }
    }

    //done
    @Override
    public ArrayList<Node> visitCond_self(XqueryParser.Cond_selfContext ctx){
        return visit(ctx.cond());
    }

    //done
    @Override
    public ArrayList<Node> visitCond_and(XqueryParser.Cond_andContext ctx){
        ArrayList<Node> temp = new ArrayList<>();
        tp = result;
        ArrayList<Node> left = visit(ctx.cond(0));
        result = tp;
        ArrayList<Node> right = visit(ctx.cond(1));
        result = tp;
        if(!left.isEmpty() && !right.isEmpty()){

            temp.addAll(right);
            temp.addAll(left);
        }
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitCond_or(XqueryParser.Cond_orContext ctx){
        ArrayList<Node> temp = new ArrayList<>();
        tp = result;
        ArrayList<Node> left = visit(ctx.cond(0));
        result = tp;
        ArrayList<Node> right = visit(ctx.cond(1));
        result = tp;
        if(!left.isEmpty() || !right.isEmpty()){
            temp.addAll(right);
            temp.addAll(left);
        }
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitCond_not(XqueryParser.Cond_notContext ctx){
        ArrayList<Node> temp = new ArrayList<>();
        tp = result;
        ArrayList<Node> mid = visit(ctx.cond());
        result = tp;
        if(mid.isEmpty()){
            temp.add(doc.createElement("empty"));
        }
        return temp;
    }


    //end of cond-------------------------------------------------------------------------------------------------------
    //done
    @Override
    public ArrayList<Node> visitWhereClause(XqueryParser.WhereClauseContext ctx){
        return visit(ctx.cond());
    }

    //done
    @Override
    public ArrayList<Node> visitLetClause(XqueryParser.LetClauseContext ctx){
        ArrayList<Node> temp = new ArrayList<>();
        ArrayList<Node> resulttemp = result;
        for(int i=0; i<ctx.xq().size(); i++){
            ArrayList<Node> tmp = new ArrayList<>();
            result = resulttemp;
            visit(ctx.xq(i));
            tmp = result;
            varHash.put(ctx.var(i).getText(), tmp);
            temp.addAll(result);
        }
        result = resulttemp;
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitReturnClause(XqueryParser.ReturnClauseContext ctx){
        // System.out.println("return clause : " + ctx.xq().getText());
        return visit(ctx.xq());
    }


//xpath %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    public ArrayList<Node> removeDup(ArrayList<Node> temp){
        for(Iterator<Node> itr = temp.iterator(); itr.hasNext();){
            Node t = itr.next();
            for(int i=(temp.indexOf(t)+1); i<temp.size(); i++){
                if(t.isSameNode(temp.get(i))){
                    itr.remove();
                    break;
                }
            }
        }
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitDoc(XqueryParser.DocContext ctx){
        File DOMtree = new File(ctx.filename().getText());
        //String filename = ctx.filename().getText();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            doc = db.parse(DOMtree);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        ArrayList<Node> a = new ArrayList<>();
        a.add(doc);
        result = a;
        return a;
    }

    //done
    @Override
    public ArrayList<Node> visitAp_children(XqueryParser.Ap_childrenContext ctx) {
        visit(ctx.doc());
        all = false;
        return visit(ctx.rp());
    }

    //done
    @Override
    public ArrayList<Node> visitAp_all(XqueryParser.Ap_allContext ctx) {
        visit(ctx.doc());
        ArrayList<Node> temp = new ArrayList<>();
        int num = result.get(0).getChildNodes().getLength();
        for (int i = 0; i < num; i++) {
            if(result.get(0).getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                temp.add(result.get(0).getChildNodes().item(i));
            }
        }
        result.addAll(temp);
        all = true;
        return visit(ctx.rp());
    }

    //done
    @Override
    public ArrayList<Node> visitRp_tagName(XqueryParser.Rp_tagNameContext ctx) {
        ArrayList<Node> temp = new ArrayList<>();
        if(all){
            temp.addAll(result);
            temp = getAllnode(temp);
            temp.removeIf(t -> !t.getNodeName().equals(ctx.VARNAME().getText()));
            if(rm){
                removeDup(temp);
                rm = false;
            }
            result = temp;
            all = false;
        }
        else {
            for (Node des : result) {
                if(des.getNodeType() == Node.DOCUMENT_NODE){
                    int num = result.get(0).getChildNodes().getLength();
                    for (int i = 0; i < num; i++) {
                        if(result.get(0).getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                            temp.add(result.get(0).getChildNodes().item(i));
                        }
                    }
                    continue;
                }
                int num = des.getChildNodes().getLength();
                for (int i = 0; i < num; i++) {
                    if (des.getChildNodes().item(i).getNodeName().equals(ctx.VARNAME().getText())) {
                        temp.add(des.getChildNodes().item(i));
                    }
                }
            }
            if(rm){
                removeDup(temp);
                rm = false;
            }
            result = temp;
        }
        return temp;
    }

    public ArrayList<Node> getAllnode (ArrayList<Node> parent){
        ArrayList<Node> temp = new ArrayList<>();
        for (Node t : parent) {
            if(t.isSameNode(doc)){
                int num = result.get(0).getChildNodes().getLength();
                for (int i = 0; i < num; i++) {
                    if(result.get(0).getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                        temp.add(result.get(0).getChildNodes().item(i));
                    }
                }
                continue;
            }
            int num = t.getChildNodes().getLength();
            if(num == 0){
                continue;
            }
            for (int i = 0; i < num; i++){
                ArrayList<Node> children = new ArrayList<>();
                if(t.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    children.add(t.getChildNodes().item(i));
                    children.addAll(getAllnode(children));
                }
                temp.addAll(children);
            }
        }
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitRp_descendant(XqueryParser.Rp_descendantContext ctx) {
        ArrayList<Node> temp = new ArrayList<>();
        if(all){
            temp.addAll(result);
            temp = getAllnode(temp);
            temp.removeIf(t -> !(t.getNodeType() == Node.ELEMENT_NODE));
            result = temp;
            if(rm){
                removeDup(temp);
                rm = false;
            }
            all = false;
        }
        else {
            for (Node des : result) {
                int num = des.getChildNodes().getLength();
                for (int i = 0; i < num; i++) {
                    if (des.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                        temp.add(des.getChildNodes().item(i));
                    }
                }
            }
            if(rm){
                removeDup(temp);
                rm = false;
            }
            result = temp;
        }
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitRp_sibling(XqueryParser.Rp_siblingContext ctx) {
        if(rm){
            removeDup(result);
            rm = false;
        }
        return result;
    }

    //done
    @Override
    public ArrayList<Node> visitRp_parent(XqueryParser.Rp_parentContext ctx) {
        ArrayList<Node> temp = new ArrayList<>();
        for(Node des : result) {
            temp.add(des.getParentNode());
        }
        if(rm){
            removeDup(temp);
            rm = false;
        }
        result = temp;
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitRp_txt(XqueryParser.Rp_txtContext ctx) {
        ArrayList<Node> temp = new ArrayList<>();
        for (Node des : result) {
            int num = des.getChildNodes().getLength();
            for(int i=0; i<num; i++) {
                if (des.getChildNodes().item(i).getNodeType() == Node.TEXT_NODE && !des.getChildNodes().item(i).getTextContent().equals("\n")) {
                    temp.add(des.getChildNodes().item(i));
                }
            }
        }
        if(rm){
            removeDup(temp);
            rm = false;
        }
        result = temp;
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitRp_attName(XqueryParser.Rp_attNameContext ctx) {
        ArrayList<Node> temp = new ArrayList<>();
        for (Node des : result) {
            int num = des.getChildNodes().getLength();
            for(int i=0; i<num; i++) {
                if (des.getChildNodes().item(i).getNodeType() == Node.ATTRIBUTE_NODE && !des.getChildNodes().item(i).getTextContent().equals("\n")) {
                    temp.add(des.getChildNodes().item(i));
                }
            }
        }
        if(rm){
            removeDup(temp);
            rm = false;
        }
        result = temp;
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitRp_self(XqueryParser.Rp_selfContext ctx) {
        if(rm){
            removeDup(result);
            rm = false;
        }
        return visit(ctx.rp());
    }

    //done
    @Override
    public ArrayList<Node> visitRp_children(XqueryParser.Rp_childrenContext ctx) {
        visit(ctx.rp(0));
        if(rm){
            removeDup(result);
            rm = false;
        }
        rm = true;
        return visit(ctx.rp(1));
    }

    //done
    @Override
    public ArrayList<Node> visitRp_all(XqueryParser.Rp_allContext ctx) {
        visit(ctx.rp(0));
        if(rm){
            removeDup(result);
            rm = false;
        }
        rm = true;
        all = true;
        return visit(ctx.rp(1));
    }

    //done
    @Override
    public ArrayList<Node> visitRp_filter(XqueryParser.Rp_filterContext ctx) {
        visit(ctx.rp());
        ArrayList <Node> cur = result;
        ArrayList <Node> ret = new ArrayList<>();
        ArrayList<Node> temp = new ArrayList<>();
        for(Node res : cur){
            ArrayList<Node> curr = new ArrayList<>();
            curr.add(res);
            result = curr;
            ret = visit(ctx.filter());
            if(ret.size()!=0){
                temp.add(res);
            }
            else{
            }
        }
        if(rm){
            removeDup(temp);
            rm = false;
        }
        result = temp;
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitRp_merge(XqueryParser.Rp_mergeContext ctx) {
        ArrayList<Node> temp;
        tp = result;
        ArrayList<Node> left = visit(ctx.rp(0));
        temp = result;
        result = tp;
        ArrayList<Node> right = visit(ctx.rp(1));
        temp.addAll(result);
        if(rm){
            removeDup(temp);
            rm = false;
        }
        result = temp;
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitFilter_rp(XqueryParser.Filter_rpContext ctx) {
        ArrayList<Node> descendant2 = result;
        ArrayList<Node> temp = new ArrayList<>();
        ArrayList<Node> descendant1 = new ArrayList<>();
        for(Node res : descendant2){
            descendant1.add(res);
            result = descendant1;
            visit(ctx.rp());
            descendant1.remove(res);
            if(!result.isEmpty()){
                temp.add(res);
            }
        }
        result = temp;
        return temp;
    }

    //what if rp(1) is 'text'?
    @Override
    public ArrayList<Node> visitFilter_equal(XqueryParser.Filter_equalContext ctx) {
        ArrayList<Node> temp = new ArrayList<>();
        tp = result;
        ArrayList<Node> left = visit(ctx.rp(0));
        result = tp;
        ArrayList<Node> right = visit(ctx.rp(1));
        for (Node des1 : left) {
            for (Node des : right) {
                if (des1.isEqualNode(des)) {
                    temp.add(des1);
                }
            }
        }
        result = temp;
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitFilter_is(XqueryParser.Filter_isContext ctx) {
        ArrayList<Node> temp = new ArrayList<>();
        tp = result;
        ArrayList<Node> left = visit(ctx.rp(0));
        result = tp;
        ArrayList<Node> right = visit(ctx.rp(1));
        for (Node des1 : left) {
            for (Node des : right) {
                if (des1.isSameNode(des)) {
                    temp.add(des1);
                }
            }
        }
        result = temp;
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitFilter_parent(XqueryParser.Filter_parentContext ctx) {
        return visit(ctx.filter());
    }

    @Override
    public ArrayList<Node> visitFilter_txt(XqueryParser.Filter_txtContext ctx) {
        ArrayList<Node> temp = new ArrayList<>();
        visit(ctx.rp());
        String txt;
        txt = ctx.STRING().getText();
        String str = txt.substring(1,(txt.length()-1));

        for(Node des : result){
            if(des.getNodeType() == Node.TEXT_NODE && des.getTextContent().equals(str)){
                temp.add(des);
            }
            else if(des.getNodeType() == Node.ATTRIBUTE_NODE && des.getTextContent().equals(str)){
                temp.add(des);
            }
        }
        result = temp;
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitFilter_and(XqueryParser.Filter_andContext ctx) {
        ArrayList<Node> temp = new ArrayList<>();
        tp = result;
        ArrayList<Node> left = visit(ctx.filter(0));
        result = tp;
        ArrayList<Node> right = visit(ctx.filter(1));
        if(left.size()!=0 && right.size()!=0){
            temp.addAll(right);
            temp.addAll(left);
        }
        result = temp;
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitFilter_or(XqueryParser.Filter_orContext ctx) {
        ArrayList<Node> temp = new ArrayList<>();
        tp = result;
        ArrayList<Node> left = visit(ctx.filter(0));
        result = tp;
        ArrayList<Node> right = visit(ctx.filter(1));
        if(left.size()!=0 || right.size()!=0){
            temp.addAll(right);
            temp.addAll(left);
        }
        result = temp;
        return temp;
    }

    //done
    @Override
    public ArrayList<Node> visitFilter_not(XqueryParser.Filter_notContext ctx) {
        ArrayList<Node> temp = new ArrayList<>();
        ArrayList<Node> mid;
        tp = result;
        mid = visit(ctx.filter());
        if(mid.size() == 0){
            result = tp;
            temp = tp;
        }
        return temp;
    }
}