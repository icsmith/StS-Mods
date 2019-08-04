package RoR_Engineer.cards;

import RoR_Engineer.actions.PressureMinesAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import RoR_Engineer.RoR_Engineer;
import RoR_Engineer.characters.The_Engineer;

import static RoR_Engineer.RoR_Engineer.makeCardPath;

public class PressureMines extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = RoR_Engineer.makeID(PressureMines.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Pressure_Mines.png");
    //Todo: Add card image
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/
    //Todo: add description text to JSON
    //Todo: add card to the game

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = The_Engineer.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DMG = 2;

    private static final int THORNS = DAMAGE;
    private static final int UPGRADE_PLUS_THORNS = UPGRADE_PLUS_DMG;

    // /STAT DECLARATION/


    public PressureMines() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = THORNS;

    }


    // Actions the card should do.
    //Todo: Pick block or damage action
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                 new PressureMinesAction(this.damage, this.magicNumber, m));

    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(UPGRADE_PLUS_THORNS);
            initializeDescription();
        }
    }
}
