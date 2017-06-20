package io.skysail.core.restlet.queries.impl

class EntityEvaluationFilterVisitor extends FilterVisitor {
  def visit(node: ExprNode): Any = node.evaluateEntity(this)
}