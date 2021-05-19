# coding_style

## Introduction

Check code style with using clang-format, and show the result with warning-ng graph.

## Getting started

This plugin is tested based on Freestyle project on Jenkins.

1. Make a Freestyle project in Jenkins.
2. Connect your github repository with Jenkins.
3. Download hpi file in target directory and upload it on Jenkins.
4. Copy or download shell file and paste or use it When build option on Jenkins.
5. After then, on Build step, select uploaded plugin, and write text file namd and format that checked on shell script(e.g text.txt)
6. On post build step, select warning ng plugin and write **/out.xml.
7. After then, if you build, it will show the checked result with graph.

## Issues

TODO Decide where you're going to host your issues, the default is Jenkins JIRA, but you can also enable GitHub issues,
If you use GitHub issues there's no need for this section; else add the following line:

Report issues and enhancements in the [Jenkins issue tracker](https://issues.jenkins-ci.org/).

## Contributing

TODO review the default [CONTRIBUTING](https://github.com/jenkinsci/.github/blob/master/CONTRIBUTING.md) file and make sure it is appropriate for your plugin, if not then add your own one adapted from the base file

Refer to our [contribution guidelines](https://github.com/jenkinsci/.github/blob/master/CONTRIBUTING.md)

## LICENSE

Licensed under MIT, see [LICENSE](LICENSE.md)

