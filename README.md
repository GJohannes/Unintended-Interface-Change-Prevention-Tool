# Unintended-Interface-Change-Prevention-Tool (UICPT)

## Description
Stand alone tool to plug into a CI/CD/CDE pipeline. Keeping an overview of all configurations that are contributing to a pipeline. Unintended changes in a pipeline configuration such as unseen updates, malicious or unauthorized changes can be prevented by stopping the pipeline execution early.   

## Usage
First of all execute the binary to create requiured files. Put paths to all configuration interface files that are contibuting to a pipeline into the configuration file of this tool (Filename: ConfigurationInterfaces.txt). Manually execute the tool with the argument: "acceptNewInterfaces" to store the binary values for future use. Plug the tool as a command line execution inside a pipeline. The argument "pipelinePart" is used for this part. Each execution will check whether a change occured. In case of a change the pipeline will be stopped and a change can manually be checked. Is a change valid the tool must be executed manually with the argument: "acceptNewInterfaces" to be up to date with the stored binary values. UICPT should be executed at the start of a pipeline to prevent any harmful side effects from unintended changes. 

## Features
- Windows and Linux Support 
- Works with each tool that has configuration file(s) independent from used language/platform
- Execution finished without exit code 0 should a change be detected 
- Specific file detection

## Important 
Prototype only. Important not supported features are:
 - a specific change location inside a file
 - entire configuration folder 
 - history 
 - networking 
 - hashing
 
## Executable File
![executable .jar File](https://s3.eu-central-1.amazonaws.com/bucket4testing23052018/UICPT_V1.1.jar)

### Internal Sequence of Events
![Activity Diagram](https://s3.eu-central-1.amazonaws.com/bucket4testing23052018/ActivityDiagramUICPTImplementation.png)
