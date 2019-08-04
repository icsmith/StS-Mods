package RoR_Engineer.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import RoR_Engineer.RoR_Engineer;
import RoR_Engineer.characters.The_Engineer;
import RoR_Engineer.orbs.AutoTurretOrb;

import static RoR_Engineer.RoR_Engineer.makeCardPath;

public class ConstructAutoTurret extends AbstractDynamicCard {

    /*
     * Orb time.
     *
     * Channel 1 Default Orb.
     * Todo: Change class name
     *  Todo: Change rarity
     */

    // TEXT DECLARATION

    public static final String ID = RoR_Engineer.makeID(ConstructAutoTurret.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("TR12_Gauss_Auto-Turret.png");

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = The_Engineer.Enums.COLOR_GRAY;

    private static final int COST = 1;

    // /STAT DECLARATION/

    public ConstructAutoTurret() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new AutoTurretOrb(this.upgraded))); // Channel a Default Orb.

    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}