package io.jenkins.plugins;

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

public class CodeStyleBuilder extends Builder implements SimpleBuildStep {

    private final String name;
    
    @DataBoundConstructor
    public CodeStyleBuilder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /* make object and conver text file to xml file */
    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
	try{	
        	converXmlCode convert = new converXmlCode();	
                String path = workspace + "/" + name;
		String currentWorkspace = workspace + "/";	
		convert.readFile(path, currentWorkspace);
	}
	catch(Exception e){
		listener.getLogger().println("Exception occured");
	}
    }

    @Symbol("greet")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
	
	/* check the report is name is given or not */
        public FormValidation doCheckName(@QueryParameter String value)
                throws IOException, ServletException {
            if (value.length() == 0)
                return FormValidation.error(Messages.CodeStyleBuilder_DescriptorImpl_errors_missingName()); 
            return FormValidation.ok();
        }
	
	/* check availability */
	@Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

	/* set plugin display name */
        @Override
        public String getDisplayName() {
            return Messages.CodeStyleBuilder_DescriptorImpl_DisplayName();
        }

    }

}
