package io.skysail.core.model

/**
 * Describes types of associations between resources.
 * 
 * For example, a SkysailApplication can define a resource to be used when there is a request to the
 * root context by setting "addAssociatedResourceClasses(List((APPLICATION_CONTEXT_RESOURCE, classOf[ConfigsResource])))". 
 */
sealed trait ResourceAssociationType 

/**
 * An {@link ApplicationModel} can define the resource which should be used to serve the 
 * root context.
 */
case object APPLICATION_CONTEXT_RESOURCE extends ResourceAssociationType

case object RESOURCE_SELF extends ResourceAssociationType
case object ENTITY_RESOURCE_FOR_LIST_RESOURCE extends ResourceAssociationType
case object LINKED_RESOURCE extends ResourceAssociationType
case object FORM_TARGET_RESOURCE extends ResourceAssociationType
