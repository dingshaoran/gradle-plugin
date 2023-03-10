import com.android.build.api.instrumentation.FramesComputationMode;
import com.android.build.api.instrumentation.InstrumentationScope;
import com.android.build.api.variant.AndroidComponentsExtension;
import com.android.build.api.variant.ApplicationVariant;
import com.android.build.api.variant.Component;
import com.android.visitor.factory.CopyFramesVisitorFactory;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Plugin entrance*/
public class ClassVisitorPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getExtensions().create("visitor", VisitorExtension.class);

        AndroidComponentsExtension androidComponents = project.getExtensions().getByType(AndroidComponentsExtension.class);
        androidComponents.onVariants(androidComponents.selector().all(), variant -> {
            if (variant instanceof Component) {
                ((Component) variant).getInstrumentation().transformClassesWith(CopyFramesVisitorFactory.class, InstrumentationScope.PROJECT, it -> {
                    VisitorExtension visitor = project.getExtensions().getByType(VisitorExtension.class);
                    it.getVisit().set(visitor.getVisit());
                    return null;
                });
                ((ApplicationVariant) variant).getInstrumentation().setAsmFramesComputationMode(FramesComputationMode.COPY_FRAMES);
            }
        });
    }
}