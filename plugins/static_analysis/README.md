## Static Analysis Status Report </br>

Using [ikos](https://github.com/NASA-SW-VnV/ikos), we create a translator that converts the results of analyzing the code to json. </br>
And show the result with warning-ng graph.

How to use 
-----------------------------------------------------------------------------------------------
1. Build docker </br>
  docker build –tag <tag name:version> <path> </br>
  docker run -it  -p 8080:8080 -p 8081:8081 <tag:version> /bin/bash  </br>
2. Connect jenkins in docker container  </br>
  service jenkins start </br>
  access localhost:8080 </br>
3. Login jenkins </br>
4. Install warnings-ng plugin </br>
5. Load static_analysis.hpi </br>
6. Create new task (free style project) </br>
7. (Source Code Management) Connect git  </br>
8. (Build) Execute execute shell  </br>
  #!/bin/sh </br>
  git diff --name-only $GIT_PREVIOUS_COMMIT $GIT_COMMIT > list.txt
  pwd > t.txt </br>
  while read line </br>
  do </br>
  read value < t.txt </br>
  /ikos/bin/ikos "${value}/${line}" </br> 
  done <list.txt > out.txt </br>
9. (Build) Select static analysis status reporter </br>
  report name : out.txt </br>
10. (Post-build) Select Record compiler warnings and static analysis results </br>
  Tool : Native Analysis Model Format </br>
  Report File Pattern : **/report.json </br>
11. Build -> Click Native Analysis Model Format Warnings </br>

Test code 
---------------------------------------------------------------------------
https://github.com/ji0859/JenkinsTest
