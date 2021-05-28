// CHECKSTYLE:OFF

package io.jenkins.plugins;

import org.jvnet.localizer.Localizable;
import org.jvnet.localizer.ResourceBundleHolder;
import org.kohsuke.accmod.Restricted;


/**
 * Generated localization support class.
 * 
 */
@SuppressWarnings({
    "",
    "PMD",
    "all"
})
@Restricted(org.kohsuke.accmod.restrictions.NoExternalUse.class)
public class Messages {

    /**
     * The resource bundle reference
     * 
     */
    private final static ResourceBundleHolder holder = ResourceBundleHolder.get(Messages.class);

    /**
     * Key {@code StaticAnalysisBuilder.DescriptorImpl.errors.missingName}:
     * {@code Please set a report name}.
     * 
     * @return
     *     {@code Please set a report name}
     */
    public static String StaticAnalysisBuilder_DescriptorImpl_errors_missingName() {
        return holder.format("StaticAnalysisBuilder.DescriptorImpl.errors.missingName");
    }

    /**
     * Key {@code StaticAnalysisBuilder.DescriptorImpl.errors.missingName}:
     * {@code Please set a report name}.
     * 
     * @return
     *     {@code Please set a report name}
     */
    public static Localizable _StaticAnalysisBuilder_DescriptorImpl_errors_missingName() {
        return new Localizable(holder, "StaticAnalysisBuilder.DescriptorImpl.errors.missingName");
    }

    /**
     * Key {@code StaticAnalysisBuilder.DescriptorImpl.DisplayName}: {@code
     * Static analysis status reporter}.
     * 
     * @return
     *     {@code Static analysis status reporter}
     */
    public static String StaticAnalysisBuilder_DescriptorImpl_DisplayName() {
        return holder.format("StaticAnalysisBuilder.DescriptorImpl.DisplayName");
    }

    /**
     * Key {@code StaticAnalysisBuilder.DescriptorImpl.DisplayName}: {@code
     * Static analysis status reporter}.
     * 
     * @return
     *     {@code Static analysis status reporter}
     */
    public static Localizable _StaticAnalysisBuilder_DescriptorImpl_DisplayName() {
        return new Localizable(holder, "StaticAnalysisBuilder.DescriptorImpl.DisplayName");
    }

}
