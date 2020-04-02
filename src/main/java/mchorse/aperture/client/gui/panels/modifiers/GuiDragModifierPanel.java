package mchorse.aperture.client.gui.panels.modifiers;

import mchorse.aperture.camera.modifiers.DragModifier;
import mchorse.aperture.client.gui.GuiModifiersManager;
import mchorse.aperture.client.gui.panels.modifiers.widgets.GuiActiveWidget;
import mchorse.mclib.client.gui.framework.elements.input.GuiTrackpadElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class GuiDragModifierPanel extends GuiAbstractModifierPanel<DragModifier>
{
    public GuiTrackpadElement factor;
    public GuiActiveWidget active;

    public GuiDragModifierPanel(Minecraft mc, DragModifier modifier, GuiModifiersManager modifiers)
    {
        super(mc, modifier, modifiers);

        this.factor = new GuiTrackpadElement(mc, (value) ->
        {
            this.modifier.factor = value;
            this.modifiers.editor.updateProfile();
        });
        this.factor.limit(0, 1).values(0.05F, 0.01F, 0.2F).increment(0.1F).tooltip(I18n.format("aperture.gui.modifiers.panels.factor"));

        this.active = new GuiActiveWidget(mc, (value) ->
        {
            this.modifier.active = value;
            this.modifiers.editor.updateProfile();
        });

        this.fields.add(this.factor, this.active);
    }

    @Override
    public void resize()
    {
        super.resize();

        this.factor.setValue(this.modifier.factor);
        this.active.value = this.modifier.active;
    }
}