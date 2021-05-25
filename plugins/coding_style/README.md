#  Coding styling check Status Reporter 
The goal is to improve readability and increase the efficiency of collaboration and maintenance by maintaining consistency in code structures(coding style)

## How to use
1. Build Dockerfile in Docker directory.
2. Execute docker image and start jenkins service (like __service jenkins start__)
3. Login Jenkins or make your account.
4. Download warning-ng plugin.
5. Make a Freestyle project in Jenkins.
6. Connect your github repository with Jenkins.
7. Download hpi file in target directory and upload it on Jenkins.
8. Copy or download shell file and paste or upload it on build option on Jenkins(stored named as __check.sh__ in this reporitory).
9. After then, on Build step, select uploaded plugin(__Coding style status Reporter__), and write text file namd and format that checked on shell script(e.g text.txt).
10. On post build step, select warning ng plugin(shown as __Record compiler warnings and static analysis results__)
11. When you choose Tools, select __Native Analysis Model Format__.
12. On report pattern, write __**/report.xml__.
13. After then, if you build, it will show the checked result with graph.

## Notes
This plugin is tested and run based on Freestyle project in Jenkins. Also to use this plugin, in your server(where Jenkins exits) has to contain coding style check tool, clang-format. If your OS is ubuntu and its version is lower than 18.04, this plugin cannot be used. It is because the option --dry-run is supported from clang-format version 10. To use this plugin either your ubuntu is not over 18.04, you can use Docker(virtual OS) ect.

## LICENSE
No LICENSE. if you want to modify, you can clone and modify as you want.

## Test code:
<https://github.com/MinjeongKim98/OSS_Class_Test.git/>
