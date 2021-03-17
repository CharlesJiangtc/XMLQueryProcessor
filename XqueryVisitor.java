// Generated from E:/IntelliJ IDEA 2020.3.1/projects/ms3/src\Xquery.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XqueryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XqueryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link XqueryParser#forClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForClause(XqueryParser.ForClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#letClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetClause(XqueryParser.LetClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClause(XqueryParser.WhereClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnClause(XqueryParser.ReturnClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(XqueryParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(XqueryParser.ListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xq_flwer}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXq_flwer(XqueryParser.Xq_flwerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xq_rpchildren}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXq_rpchildren(XqueryParser.Xq_rpchildrenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xq_Stringconstant}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXq_Stringconstant(XqueryParser.Xq_StringconstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xq_rpall}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXq_rpall(XqueryParser.Xq_rpallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xq_variable}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXq_variable(XqueryParser.Xq_variableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xq_ap}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXq_ap(XqueryParser.Xq_apContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xq_join}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXq_join(XqueryParser.Xq_joinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xq_merge}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXq_merge(XqueryParser.Xq_mergeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xq_self}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXq_self(XqueryParser.Xq_selfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xq_tag}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXq_tag(XqueryParser.Xq_tagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xq_let}
	 * labeled alternative in {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXq_let(XqueryParser.Xq_letContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cond_and}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond_and(XqueryParser.Cond_andContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cond_empty}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond_empty(XqueryParser.Cond_emptyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cond_self}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond_self(XqueryParser.Cond_selfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cond_equal}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond_equal(XqueryParser.Cond_equalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cond_is}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond_is(XqueryParser.Cond_isContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cond_satisfy}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond_satisfy(XqueryParser.Cond_satisfyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cond_not}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond_not(XqueryParser.Cond_notContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cond_or}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond_or(XqueryParser.Cond_orContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#filename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilename(XqueryParser.FilenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#doc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoc(XqueryParser.DocContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ap_children}
	 * labeled alternative in {@link XqueryParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAp_children(XqueryParser.Ap_childrenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ap_all}
	 * labeled alternative in {@link XqueryParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAp_all(XqueryParser.Ap_allContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rp_txt}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp_txt(XqueryParser.Rp_txtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rp_attName}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp_attName(XqueryParser.Rp_attNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rp_parent}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp_parent(XqueryParser.Rp_parentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rp_sibling}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp_sibling(XqueryParser.Rp_siblingContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rp_descendant}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp_descendant(XqueryParser.Rp_descendantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rp_self}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp_self(XqueryParser.Rp_selfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rp_children}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp_children(XqueryParser.Rp_childrenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rp_tagName}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp_tagName(XqueryParser.Rp_tagNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rp_merge}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp_merge(XqueryParser.Rp_mergeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rp_filter}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp_filter(XqueryParser.Rp_filterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rp_all}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp_all(XqueryParser.Rp_allContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter_rp}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_rp(XqueryParser.Filter_rpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter_and}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_and(XqueryParser.Filter_andContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter_parent}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_parent(XqueryParser.Filter_parentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter_is}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_is(XqueryParser.Filter_isContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter_txt}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_txt(XqueryParser.Filter_txtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter_equal}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_equal(XqueryParser.Filter_equalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter_or}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_or(XqueryParser.Filter_orContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter_not}
	 * labeled alternative in {@link XqueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_not(XqueryParser.Filter_notContext ctx);
}