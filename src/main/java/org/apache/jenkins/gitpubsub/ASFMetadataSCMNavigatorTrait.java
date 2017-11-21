package org.apache.jenkins.gitpubsub;

import hudson.Extension;
import javax.annotation.Nonnull;
import jenkins.scm.api.SCMNavigator;
import jenkins.scm.api.trait.SCMNavigatorContext;
import jenkins.scm.api.trait.SCMNavigatorTrait;
import jenkins.scm.api.trait.SCMNavigatorTraitDescriptor;
import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;

public class ASFMetadataSCMNavigatorTrait extends SCMNavigatorTrait {
    private final String avatarUrl;
    private final String avatarDescription;
    private final String objectDisplayName;
    private final String objectDescription;
    private final String objectUrl;

    @DataBoundConstructor
    public ASFMetadataSCMNavigatorTrait(String avatarUrl, String avatarDescription, String objectDisplayName,
                                        String objectDescription, String objectUrl) {
        this.avatarUrl = StringUtils.trimToNull(avatarUrl);
        this.avatarDescription = StringUtils.trimToNull(avatarDescription);
        this.objectDisplayName = StringUtils.trimToNull(objectDisplayName);
        this.objectDescription = StringUtils.trimToNull(objectDescription);
        this.objectUrl = StringUtils.trimToNull(objectUrl);
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getAvatarDescription() {
        return avatarDescription;
    }

    public String getObjectDisplayName() {
        return objectDisplayName;
    }

    public String getObjectDescription() {
        return objectDescription;
    }

    public String getObjectUrl() {
        return objectUrl;
    }

    @Override
    protected void decorateContext(SCMNavigatorContext<?, ?> context) {
        ((ASFGitSCMNavigatorContext) context)
                .withAvatarUrl(avatarUrl)
                .withAvatarDescription(avatarDescription)
                .withObjectUrl(objectUrl)
                .withObjectDisplayName(objectDisplayName)
                .withAvatarDescription(objectDescription);
    }

    @Extension
    public static class DescriptorImpl extends SCMNavigatorTraitDescriptor {
        @Override
        public Class<? extends SCMNavigatorContext> getContextClass() {
            return ASFGitSCMNavigatorContext.class;
        }

        @Override
        public Class<? extends SCMNavigator> getNavigatorClass() {
            return ASFGitSCMNavigator.class;
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return Messages.ASFMetadataSCMNavigatorTrait_displayName();
        }
    }
}