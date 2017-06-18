package io.skysail.core.restlet.queries.impl

import io.skysail.core.restlet.queries.impl.ExprNode

class EntityEvaluationFilterVisitor extends FilterVisitor {
  def visit(node: ExprNode): Any = node.evaluateEntity(this)
}