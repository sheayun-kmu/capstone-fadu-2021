package io.jenkins.plugins.sample;

import hudson.Launcher;
import hudson.Extension;
import hudson.FilePath;
import hudson.util.FormValidation;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;
import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundSetter;

import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

public class HelloWorldBuilder extends Builder implements SimpleBuildStep {

    private final String name;
    private boolean useFrench;
/*
    @Override
    public String getIconFileName(){
	    return "document.png";
    }

    @Override
    public String getDisplayName(){
	    return "Greeting";
    }

    @Override
    public String getUrlName(){
	    return "greeting";
    }
*/
    @DataBoundConstructor
    public HelloWorldBuilder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isUseFrench() {
        return useFrench;
    }

    @DataBoundSetter
    public void setUseFrench(boolean useFrench) {
        this.useFrench = useFrench;
    }

    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
	try{
		run.addAction(new Actions_sidePanel(name));	
		if (useFrench) {
            		listener.getLogger().println("Bonjour, " + name + "!");
        	} else {
            		listener.getLogger().println("Hello, " + name + "!");
        		convertToXml convert = new convertToXml();
                	String path = convert.readFile("/var/jenkins_home/workspace/github_test/text.txt");
                	xmlParser parser = new xmlParser();
                	Report report = parser.extractInfo(path);
                	System.out.println(report);
                	List<List> t = report.tmp();
                	for(int i = 0; i < t.size(); i++) {
                        	if(!t.get(i).isEmpty()) {
                                	listener.getLogger().println(t.get(i));
                        	}
                	}

		}
	}
	catch(ParserConfigurationException e){
		System.out.println("a");	
	}
	catch(TransformerException e){
		System.out.println("b");
	}
	catch(SAXException e){
		System.out.println("c");
	}
    }

    @Symbol("greet")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public FormValidation doCheckName(@QueryParameter String value, @QueryParameter boolean useFrench)
                throws IOException, ServletException {
            if (value.length() == 0)
                return FormValidation.error(Messages.HelloWorldBuilder_DescriptorImpl_errors_missingName());
            if (value.length() < 4)
                return FormValidation.warning(Messages.HelloWorldBuilder_DescriptorImpl_warnings_tooShort());
            if (!useFrench && value.matches(".*[éáàç].*")) {
                return FormValidation.warning(Messages.HelloWorldBuilder_DescriptorImpl_warnings_reallyFrench());
            }
            return FormValidation.ok();
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return Messages.HelloWorldBuilder_DescriptorImpl_DisplayName();
        }

    }

}
