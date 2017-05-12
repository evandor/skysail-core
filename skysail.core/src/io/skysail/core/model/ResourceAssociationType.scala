package io.skysail.core.model

sealed trait ResourceAssociationType 

case object APPLICATION_CONTEXT_RESOURCE extends ResourceAssociationType
case object RESOURCE_SELF extends ResourceAssociationType
case object ENTITY_RESOURCE_FOR_LIST_RESOURCE extends ResourceAssociationType
case object LINKED_RESOURCE extends ResourceAssociationType
case object FORM_TARGET_RESOURCE extends ResourceAssociationType
