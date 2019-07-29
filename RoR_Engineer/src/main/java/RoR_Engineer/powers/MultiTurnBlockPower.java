package RoR_Engineer.powers;

import RoR_Engineer.RoR_Engineer;
import RoR_Engineer.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static RoR_Engineer.RoR_Engineer.makePowerPath;


public class MultiTurnBlockPower extends AbstractPower {

    private static int armorAmount;

    public static final String POWER_ID = RoR_Engineer.makeID("MultiTurnBlockPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    //Todo: Add a real texture
    //Todo: add description text to JSON

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("Bubble_Shield.jpg"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("Bubble_Shield.jpg"));

    public MultiTurnBlockPower(AbstractCreature owner, int armorAmt, int numTurns) {
        name = NAME;
        ID = POWER_ID;

        armorAmount = armorAmt;

        this.owner = owner;
        this.amount = numTurns;


        type = PowerType.BUFF;
        isTurnBased = true;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }



    @Override
    public void atEndOfTurn(boolean isPlayer){
        if (amount != 0){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, armorAmount));
            amount --;
        }else{
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
        updateDescription();

    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else if (amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }
}
