package xyz.qalcyo.wyvtils.config

import gg.essential.api.EssentialAPI
import gg.essential.universal.ChatColor
import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import net.minecraft.client.MinecraftClient
import xyz.qalcyo.wyvtils.Wyvtils
import xyz.qalcyo.wyvtils.gui.ActionBarGui
import xyz.qalcyo.wyvtils.gui.BossBarGui
import xyz.qalcyo.wyvtils.gui.SidebarGui
import java.awt.Color
import java.io.File

@Suppress("unused")
object WyvtilsConfig : Vigilant(
    File(Wyvtils.modDir, "wyvtils.toml"),
    "${ChatColor.DARK_PURPLE}Wyvtils"
) {

    @Property(
        type = PropertyType.SWITCH,
        name = "Disable Text Shadow",
        description = "Disable the shadow on text rendering. Can boost performance.",
        category = "Miscellaneous"
    )
    var disableTextShadow = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Render Own Nametag",
        description = "Render your own nametag in third person.",
        category = "Miscellaneous",
        subcategory = "Nametags"
    )
    var renderOwnNametag = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Remove Nametag Background",
        description = "Remove the background of nametags.",
        category = "Miscellaneous",
        subcategory = "Nametags"
    )
    var removeNametagBackground = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Nametag Text Shadow",
        description = "Render a text shadow with the nametag text.",
        category = "Miscellaneous",
        subcategory = "Nametags"
    )
    var nametagTextShadow = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Bossbar",
        description = "Toggle the bossbar.",
        category = "Bossbar"
    )
    var bossBar = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Text",
        description = "Toggle the text for the bossbar.",
        category = "Bossbar"
    )
    var bossBarText = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Shadow",
        description = "Toggle the text shadow for the bossbar.",
        category = "Bossbar"
    )
    var bossBarShadow = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Bar",
        description = "Toggle the bar for the bossbar.",
        category = "Bossbar"
    )
    var bossBarBar = true

    @Property(
        type = PropertyType.PERCENT_SLIDER,
        name = "Bossbar Scale",
        description = "Set the scale for the bossbar.",
        category = "Bossbar"
    )
    var bossbarScale = 1.0F

    @Property(
        type = PropertyType.BUTTON,
        name = "Bossbar Editor",
        description = "Change the position of the bossbar.",
        category = "Bossbar"
    )
    fun openBossHealthGui() {
        EssentialAPI.getGuiUtil().openScreen(BossBarGui(gui()))
    }

    @Property(
        type = PropertyType.BUTTON,
        name = "Reset Position",
        description = "Reset the position of the bossbar to its original position.",
        category = "Bossbar"
    )
    fun resetBossbar() {
        EssentialAPI.getGuiUtil().openScreen(null)
        bossBarX = MinecraftClient.getInstance().window.scaledWidth
        bossBarY = 12
        WyvtilsConfig.markDirty()
        WyvtilsConfig.writeData()
        EssentialAPI.getGuiUtil().openScreen(gui())
    }

    @Property(
        type = PropertyType.NUMBER,
        name = "Bossbar X",
        description = "X",
        category = "Bossbar",
        hidden = true
    )
    var bossBarX: Int = 0

    @Property(
        type = PropertyType.NUMBER,
        name = "Bossbar Y",
        description = "Y",
        category = "Bossbar",
        hidden = true
    )
    var bossBarY: Int = 12


    @Property(
        type = PropertyType.SWITCH,
        name = "bossbar first time",
        description = "a",
        category = "General",
        hidden = true
    )
    var firstLaunchBossbar = true

    @Property(
        type = PropertyType.COLOR,
        name = "Debug HUD Color",
        description = "Set the color of the Debug HUD background color.",
        category = "Debug"
    )
    var debugColor: Color = Color(-1873784752)

    @Property(
        type = PropertyType.SWITCH,
        name = "Debug HUD Shadow",
        description = "Turn on / off the shadow of Debug HUD text.",
        category = "Debug"
    )
    var debugShadow = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Action Bar Customization",
        description = "Toggle customization of the action bar.",
        category = "Action Bar"
    )
    var actionBarCustomization = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Action Bar",
        description = "Toggle the action bar.",
        category = "Action Bar"
    )
    var actionBar = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Action Bar Shadow",
        description = "Toggle the action bar shadow.",
        category = "Action Bar"
    )
    var actionBarShadow = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Action Bar Position",
        description = "Toggle the action bar position customization.",
        category = "Action Bar"
    )
    var actionBarPosition = false

    @Property(
        type = PropertyType.BUTTON,
        name = "Action Bar Editor",
        description = "Change the position of the action bar.",
        category = "Action Bar"
    )
    fun openActionBarGui() {
        if (actionBarPosition && actionBarCustomization) EssentialAPI.getGuiUtil().openScreen(ActionBarGui(gui()))
        else EssentialAPI.getNotifications().push("Wyvtils", "You don't have Action Bar Position enabled!")
    }

    @Property(
        type = PropertyType.NUMBER,
        name = "Action Bar X",
        description = "X",
        category = "Action Bar",
        hidden = true
    )
    var actionBarX: Int = 0

    @Property(
        type = PropertyType.NUMBER,
        name = "Action Bar Y",
        description = "Y",
        category = "Action Bar",
        hidden = true
    )
    var actionBarY: Int = 12

    @Property(
        type = PropertyType.PERCENT_SLIDER,
        name = "Title Scale",
        description = "Set the scale for the title.",
        category = "Title"
    )
    var titleScale = 1.0F

    @Property(
        type = PropertyType.PERCENT_SLIDER,
        name = "Subtitle Scale",
        description = "Set the scale for the subtitle.",
        category = "Title"
    )
    var subtitleScale = 1.0F

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Hitbox",
        category = "Hitbox",
        description = "Toggle the hitbox of entities."
    )
    var hitbox = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Hitboxes for Non-Players",
        category = "Hitbox",
        description = "Toggle the hitboxes of non-players."
    )
    var nonplayerHitbox = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Force Hitboxes",
        category = "Hitbox",
        description = "Force the rendering of hitbox of entities."
    )
    var forceHitbox = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Disable for Self",
        category = "Hitbox",
        description = "Don't render the hitbox if the player's hitbox is you."
    )
    var disableForSelf = false

    @Property(
        type = PropertyType.COLOR,
        name = "Hitbox Color",
        category = "Hitbox",
        description = "Change the color of the hitbox.",
    )
    var hitboxColor: Color = Color.WHITE

    @Property(
        type = PropertyType.COLOR,
        name = "Hitbox Color (within crosshair)",
        category = "Hitbox",
        description = "Change the color of the hitbox of players when they are within the crosshair of the player.",
    )
    var hitboxRangeColor: Color = Color.RED

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Line Hitbox",
        category = "Hitbox",
        description = "Toggle the eye line hitbox of entities."
    )
    var hitboxEyeLine = true

    @Property(
        type = PropertyType.COLOR,
        name = "Hitbox Line Color",
        category = "Hitbox",
        description = "Change the color of the hitbox eye line.",
    )
    var hitboxEyelineColor: Color = Color(0, 0, 255, 255)

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Hitbox Line of Sight",
        category = "Hitbox",
        description = "Toggle the hitbox of entities."
    )
    var hitboxLineOfSight = true


    @Property(
        type = PropertyType.COLOR,
        name = "Line of Sight Color",
        category = "Hitbox",
        description = "Change the color of the hitbox's line of sight.",
    )
    var hitboxLineOfSightColor: Color = Color(255, 0, 0, 255)

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Sidebar",
        category = "Sidebar",
        description = "Toggle the sidebar from rendering."
    )
    var sidebar = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Sidebar Text Shadow",
        category = "Sidebar",
        description = "Toggle the sidebar text's shadow from rendering."
    )
    var sidebarTextShadow = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Score Points",
        category = "Sidebar",
        description = "Toggle the sidebar score points (aka red numbers) from rendering."
    )
    var sidebarScorePoints = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Background",
        category = "Sidebar",
        description = "Toggle the background from rendering."
    )
    var sidebarBackground = true

    @Property(
        type = PropertyType.COLOR,
        name = "Sidebar Background Color",
        category = "Sidebar",
        description = "Change the text color for the sidebar."
    )
    var sidebarBackgroundColor: Color = Color(0, 0, 0, 50)

    @Property(
        type = PropertyType.SWITCH,
        name = "Background Border",
        category = "Sidebar",
        description = "Enable a border to border the background."
    )
    var backgroundBorder = false

    @Property(
        type = PropertyType.COLOR,
        name = "Border Color",
        category = "Sidebar",
        description = "Select a color to set for the background border."
    )
    var borderColor: Color = Color.WHITE

    @Property(
        type = PropertyType.NUMBER,
        name = "Border Width",
        category = "Sidebar",
        description = "Set the width of the background border.",
        min = 1,
        max = 20
    )
    var borderNumber = 2

    @Property(
        type = PropertyType.SWITCH,
        name = "Sidebar Position",
        category = "Sidebar",
        description = "Toggle the sidebar position editor."
    )
    var sidebarPosition = false

    @Property(
        type = PropertyType.BUTTON,
        name = "Sidebar Editor",
        description = "Change the position of the sidebar.",
        category = "Sidebar"
    )
    fun openSidebarGui() {
        if (sidebarPosition) EssentialAPI.getGuiUtil().openScreen(SidebarGui(gui()))
        else EssentialAPI.getNotifications().push("Wyvtils", "You don't have Sidebar Position enabled!")
    }

    @Property(
        type = PropertyType.PERCENT_SLIDER,
        name = "Sidebar Scale",
        description = "Set the scale for the sidebar.",
        category = "Sidebar"
    )
    var sidebarScale = 1.0F

    @Property(
        type = PropertyType.NUMBER,
        name = "Sidebar X",
        description = "X",
        category = "Sidebar",
        hidden = true
    )
    var sidebarX: Int = 0

    @Property(
        type = PropertyType.NUMBER,
        name = "Sidebar Y",
        description = "Y",
        category = "Sidebar",
        hidden = true
    )
    var sidebarY: Int = 12

    fun markAndWrite() {
        markDirty()
        writeData()
    }

    init {
        initialize()
    }

}