package RoR_Engineer.cards;

import RoR_Engineer.RoR_Engineer;
import RoR_Engineer.actions.GrenadeLauncherAction;
import RoR_Engineer.characters.The_Engineer;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static RoR_Engineer.RoR_Engineer.makeCardPath;

public class GrenadeLauncher extends AbstractDynamicCard {



    // TEXT DECLARATION

    public static final String ID = RoR_Engineer.makeID(GrenadeLauncher.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Bouncing_Grenades.png"); //TODO: Replace card art
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = The_Engineer.Enums.COLOR_GRAY;

    private static final int COST = -1;  // COST = X
    private static final int UPGRADED_COST = -1; // UPGRADED_COST = X

    private static final int DAMAGE = 5;    // DAMAGE = 6
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = 2

    // /STAT DECLARATION/


    public GrenadeLauncher() { //- This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.isMultiDamage = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (energyOnUse < EnergyPanel.totalCount) {
            energyOnUse = EnergyPanel.totalCount;
        }

        AbstractDungeon.actionManager.addToBottom(new GrenadeLauncherAction(p, this.multiDamage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
