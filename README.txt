0. Required: Eclipse, Maven apache (download zip file, unzip and set Path of System variable to \bin)
1. Clone repository
2. Import project to Eclipse. 
If project is not marked as Cucumber project: Right click project \ Configure \Convert to Cucumber project
3. Right click Project\Maven\Update project...to get all required dependencies
4. Install some Eclipse plugin: Help\Eclipse Marketplace: install Cucumber Eclipse Plugin, TestNG for Eclipse
5. Run test: in source folder: mvn test
Run with parameters: mvn -Dbrowser=${browser} -DURL = ${URL} -Dcucumber.filter.tags="@{tag}" -Ddataproviderthreadcount=${threadNumber} test