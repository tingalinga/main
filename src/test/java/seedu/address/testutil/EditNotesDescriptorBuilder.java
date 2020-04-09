package seedu.address.testutil;

import seedu.address.logic.commands.notes.NotesEditCommand.EditNotesDescriptor;
import seedu.address.model.notes.Notes;

/**
 * A utility class which builds EditNotesDescriptor objects.
 */
public class EditNotesDescriptorBuilder {
    private EditNotesDescriptor descriptor;

    public EditNotesDescriptorBuilder() {
        descriptor = new EditNotesDescriptor();
    }

    public EditNotesDescriptorBuilder(EditNotesDescriptor descriptor) {
        this.descriptor = new EditNotesDescriptor(descriptor);
    }

    /**
     * Returns a {@code EditNotesDescriptor} with fields containing {@code note}'s details
     */
    public EditNotesDescriptorBuilder(Notes note) {
        descriptor = new EditNotesDescriptor();
        descriptor.setStudent(note.getStudent());
        descriptor.setContent(note.getContent());
        descriptor.setPriority(note.getPriority());
    }

    /**
     * Sets the student field of EditNotesDescriptor that we are building.
     */
    public EditNotesDescriptorBuilder withStudent(String name) {
        descriptor.setStudent(name);
        return this;
    }

    /**
     * Sets the content field of EditNotesDescriptor that we are building.
     */
    public EditNotesDescriptorBuilder withContent(String content) {
        descriptor.setContent(content);
        return this;
    }

    /**
     * Sets the priority field of EditNotesDescriptor that we are building.
     */
    public EditNotesDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(priority);
        return this;
    }

    public EditNotesDescriptor build() {
        return descriptor;
    }

}
