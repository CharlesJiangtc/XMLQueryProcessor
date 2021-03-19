// Generated from E:/IntelliJ IDEA 2020.3.1/projects/ms3/src\Xquery.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XqueryParser}.
 */
public interface XqueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link XqueryParser#forClause}.
	 * @param ctx the parse tree
	 */
	void enterForClause(XqueryParser.ForClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#forClause}.
	 * @param ctx the parse tree
	 */
	void exitForClause(XqueryParser.ForClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#letClause}.
	 * @param ctx the parse tree
	 */
	void enterLetClause(XqueryParser.LetClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#letClause}.
	 * @param ctx the parse tree
	 */
	void exitLetClause(XqueryParser.LetClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(XqueryParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(XqueryParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterReturnClause(XqueryParser.ReturnClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitReturnClause(XqueryParser.ReturnClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(XqueryParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(XqueryParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(XqueryParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(XqueryParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xq_flwer}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXq_flwer(XqueryParser.Xq_flwerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xq_flwer}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXq_flwer(XqueryParser.Xq_flwerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xq_rpchildren}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXq_rpchildren(XqueryParser.Xq_rpchildrenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xq_rpchildren}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXq_rpchildren(XqueryParser.Xq_rpchildrenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xq_Stringconstant}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXq_Stringconstant(XqueryParser.Xq_StringconstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xq_Stringconstant}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXq_Stringconstant(XqueryParser.Xq_StringconstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xq_rpall}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXq_rpall(XqueryParser.Xq_rpallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xq_rpall}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXq_rpall(XqueryParser.Xq_rpallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xq_variable}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXq_variable(XqueryParser.Xq_variableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xq_variable}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXq_variable(XqueryParser.Xq_variableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xq_ap}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXq_ap(XqueryParser.Xq_apContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xq_ap}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXq_ap(XqueryParser.Xq_apContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xq_join}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXq_join(XqueryParser.Xq_joinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xq_join}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXq_join(XqueryParser.Xq_joinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xq_merge}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXq_merge(XqueryParser.Xq_mergeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xq_merge}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXq_merge(XqueryParser.Xq_mergeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xq_self}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXq_self(XqueryParser.Xq_selfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xq_self}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXq_self(XqueryParser.Xq_selfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xq_tag}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXq_tag(XqueryParser.Xq_tagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xq_tag}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXq_tag(XqueryParser.Xq_tagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xq_let}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXq_let(XqueryParser.Xq_letContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xq_let}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXq_let(XqueryParser.Xq_letContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cond_and}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond_and(XqueryParser.Cond_andContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cond_and}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond_and(XqueryParser.Cond_andContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cond_empty}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond_empty(XqueryParser.Cond_emptyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cond_empty}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond_empty(XqueryParser.Cond_emptyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cond_self}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond_self(XqueryParser.Cond_selfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cond_self}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond_self(XqueryParser.Cond_selfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cond_equal}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond_equal(XqueryParser.Cond_equalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cond_equal}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond_equal(XqueryParser.Cond_equalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cond_is}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond_is(XqueryParser.Cond_isContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cond_is}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond_is(XqueryParser.Cond_isContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cond_satisfy}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond_satisfy(XqueryParser.Cond_satisfyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cond_satisfy}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond_satisfy(XqueryParser.Cond_satisfyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cond_not}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond_not(XqueryParser.Cond_notContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cond_not}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond_not(XqueryParser.Cond_notContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cond_or}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond_or(XqueryParser.Cond_orContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cond_or}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond_or(XqueryParser.Cond_orContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#filename}.
	 * @param ctx the parse tree
	 */
	void enterFilename(XqueryParser.FilenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#filename}.
	 * @param ctx the parse tree
	 */
	void exitFilename(XqueryParser.FilenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#doc}.
	 * @param ctx the parse tree
	 */
	void enterDoc(XqueryParser.DocContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#doc}.
	 * @param ctx the parse tree
	 */
	void exitDoc(XqueryParser.DocContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ap_children}
	 * labeled alternative in {@link XqueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterAp_children(XqueryParser.Ap_childrenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ap_children}
	 * labeled alternative in {@link XqueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitAp_children(XqueryParser.Ap_childrenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ap_all}
	 * labeled alternative in {@link XqueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterAp_all(XqueryParser.Ap_allContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ap_all}
	 * labeled alternative in {@link XqueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitAp_all(XqueryParser.Ap_allContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rp_txt}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp_txt(XqueryParser.Rp_txtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rp_txt}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp_txt(XqueryParser.Rp_txtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rp_attName}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp_attName(XqueryParser.Rp_attNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rp_attName}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp_attName(XqueryParser.Rp_attNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rp_parent}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp_parent(XqueryParser.Rp_parentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rp_parent}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp_parent(XqueryParser.Rp_parentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rp_sibling}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp_sibling(XqueryParser.Rp_siblingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rp_sibling}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp_sibling(XqueryParser.Rp_siblingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rp_descendant}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp_descendant(XqueryParser.Rp_descendantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rp_descendant}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp_descendant(XqueryParser.Rp_descendantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rp_self}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp_self(XqueryParser.Rp_selfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rp_self}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp_self(XqueryParser.Rp_selfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rp_children}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp_children(XqueryParser.Rp_childrenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rp_children}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp_children(XqueryParser.Rp_childrenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rp_tagName}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp_tagName(XqueryParser.Rp_tagNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rp_tagName}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp_tagName(XqueryParser.Rp_tagNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rp_merge}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp_merge(XqueryParser.Rp_mergeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rp_merge}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp_merge(XqueryParser.Rp_mergeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rp_filter}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp_filter(XqueryParser.Rp_filterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rp_filter}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp_filter(XqueryParser.Rp_filterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rp_all}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp_all(XqueryParser.Rp_allContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rp_all}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp_all(XqueryParser.Rp_allContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter_rp}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter_rp(XqueryParser.Filter_rpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter_rp}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter_rp(XqueryParser.Filter_rpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter_and}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter_and(XqueryParser.Filter_andContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter_and}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter_and(XqueryParser.Filter_andContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter_parent}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter_parent(XqueryParser.Filter_parentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter_parent}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter_parent(XqueryParser.Filter_parentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter_is}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter_is(XqueryParser.Filter_isContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter_is}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter_is(XqueryParser.Filter_isContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter_txt}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter_txt(XqueryParser.Filter_txtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter_txt}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter_txt(XqueryParser.Filter_txtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter_equal}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter_equal(XqueryParser.Filter_equalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter_equal}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter_equal(XqueryParser.Filter_equalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter_or}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter_or(XqueryParser.Filter_orContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter_or}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter_or(XqueryParser.Filter_orContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter_not}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter_not(XqueryParser.Filter_notContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter_not}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter_not(XqueryParser.Filter_notContext ctx);
}