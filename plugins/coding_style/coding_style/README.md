# coding_style

## Introduction

Check code style using clang-format, and show the result with warning-ng graph.

## How to use

1. Make a Freestyle project in Jenkins.
2. Connect your github repository with Jenkins.
3. Download hpi file in target directory and upload it on Jenkins.
4. Copy or download shell file and paste or upload it on build option on Jenkins.
5. After then, on Build step, select uploaded plugin, and write text file namd and format that checked on shell script(e.g text.txt).
6. On post build step, select warning ng plugin(shown as __Record compiler warnings and static analysis results__)
7. When you choose Tools, select __Native Analysis Model Format__.
8. On report pattern, write __**/report.xml__.
9. After then, if you build, it will show the checked result with graph.

## Notes

This plugin is tested and run based on Freestyle project in Jenkins.
Also to use this plugin, in your server(where Jenkins exits) has to contain coding style check tool, clang-format.
If your OS is ubuntu and its version is lower than 18.04, this plugin cannot be used.
It is because the option __--dry-run__ is supported from clang-format version 10.
To use this plugin, you can use Docker of Virtual Machine ect.


## LICENSE

No LICENSE. if you want to modify, you can clone and modify as you want.
