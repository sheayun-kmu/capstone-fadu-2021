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
     * Key {@code CodeStyleBuilder.DescriptorImpl.errors.missingName}: {@code
     * Please set a report name}.
     * 
     * @return
     *     {@code Please set a report name}
     */
    public static String CodeStyleBuilder_DescriptorImpl_errors_missingName() {
        return holder.format("CodeStyleBuilder.DescriptorImpl.errors.missingName");
    }

    /**
     * Key {@code CodeStyleBuilder.DescriptorImpl.errors.missingName}: {@code
     * Please set a report name}.
     * 
     * @return
     *     {@code Please set a report name}
     */
    public static Localizable _CodeStyleBuilder_DescriptorImpl_errors_missingName() {
        return new Localizable(holder, "CodeStyleBuilder.DescriptorImpl.errors.missingName");
    }

    /**
     * Key {@code CodeStyleBuilder.DescriptorImpl.DisplayName}: {@code Coding
     * style status reporter}.
     * 
     * @return
     *     {@code Coding style status reporter}
     */
    public static String CodeStyleBuilder_DescriptorImpl_DisplayName() {
        return holder.format("CodeStyleBuilder.DescriptorImpl.DisplayName");
    }

    /**
     * Key {@code CodeStyleBuilder.DescriptorImpl.DisplayName}: {@code Coding
     * style status reporter}.
     * 
     * @return
     *     {@code Coding style status reporter}
     */
    public static Localizable _CodeStyleBuilder_DescriptorImpl_DisplayName() {
        return new Localizable(holder, "CodeStyleBuilder.DescriptorImpl.DisplayName");
    }

}
