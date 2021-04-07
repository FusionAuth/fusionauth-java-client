## Developer information

If you are making updates to this library, please keep these items in mind:

* Domain objects should be placed in `src/main/java/io/fusionauth/domain`
* Domain objects should generally be placed in the root package and use class names that separate them from everything else. Sub-packages should be avoided due to issues with typed languages that have poor namespacing support.
    * i.e. `Entity.java` should not be placed in a sub-package. 
* API request objects should be placed in `src/main/java/io/fusionauth/domain/api`
* API request objects should be placed in the root package and use class names that include the domain name
    * i.e. `EntityTypeSearchRequest.java` rather than `entity/type/SearchRequest.java`