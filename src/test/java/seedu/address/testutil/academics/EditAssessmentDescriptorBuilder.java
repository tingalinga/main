package seedu.address.testutil.academics;

import seedu.address.logic.commands.academics.AcademicsEditCommand.EditAssessmentDescriptor;
import seedu.address.model.academics.Assessment;

/**
 * A utility class to help with building EditAssessmentDescriptor objects.
 */
public class EditAssessmentDescriptorBuilder {

    private EditAssessmentDescriptor descriptor;

    public EditAssessmentDescriptorBuilder() {
        descriptor = new EditAssessmentDescriptor();
    }

    public EditAssessmentDescriptorBuilder(EditAssessmentDescriptor descriptor) {
        this.descriptor = new EditAssessmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing {@code student}'s details
     */
    public EditAssessmentDescriptorBuilder(Assessment assessment) {
        descriptor = new EditAssessmentDescriptor();
        descriptor.setDescription(assessment.getDescription());
        descriptor.setType(assessment.getType());
        descriptor.setDate(assessment.getDate().toString());
    }

    /**
     * Sets the {@code Description} of the {@code EditAssessmentDescriptor} that we are building.
     */
    public EditAssessmentDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(description);
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code EditAssessmentDescriptor} that we are building.
     */
    public EditAssessmentDescriptorBuilder withType(String type) {
        descriptor.setType(type);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditAssessmentDescriptor} that we are building.
     */
    public EditAssessmentDescriptorBuilder withDate(String date) {
        descriptor.setDate(date);
        return this;
    }

    public EditAssessmentDescriptor build() {
        return descriptor;
    }
}
