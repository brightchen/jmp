package cg.gwt.components.shared.data;



/*
 * resource data is the data which get from the resource property file
 * ResourceData is Top level class of resource data
 * We should make this type of class as thin as possible as it will be transit between web client and server.
 * We should NOT keep the other information such as the resource file and resource key in this class
 * as the web client don't care these information and fatten this class. 
 *
 * We can catch and keep only one instance for each locale. the different users can share same ResourceData instance 
 * when using same locale 
 */
public class ResourceData
{
}
