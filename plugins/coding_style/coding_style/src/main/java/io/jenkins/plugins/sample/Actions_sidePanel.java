package io.jenkins.plugins.sample;

import hudson.model.Action;

public class Actions_sidePanel implements Action {
	private String name;

        public Actions_sidePanel(String name){
                this.name = name;
        }

        @Override
        public String getIconFileName(){
                return "document.png";
        }

        @Override
        public String getDisplayName(){
                return "CodeStylingStatus";
        }

        @Override
        public String getUrlName(){
                return "codeStylingStatus";
        }
}
