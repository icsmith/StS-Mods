package RoR_Engineer.cards;

import RoR_Engineer.RoR_Engineer;
import RoR_Engineer.characters.The_Engineer;
import RoR_Engineer.powers.MultiTurnBlockPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static RoR_Engineer.RoR_Engineer.makeCardPath;

public class BubbleShield extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = RoR_Engineer.makeID(BubbleShield.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Bubble_Shield.jpg");

    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/

    //Todo: add description text to JSON
    //Todo: Add a bubble effect


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = The_Engineer.Enums.COLOR_GRAY;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 3;

    private static final int BLOCK = 10;
    private static final int UPGRADE_PLUS_BLOCK =  5;

    private static final int TURNS = 3;

    // /STAT DECLARATION/


    public  BubbleShield() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
    }


    // Actions the card should do.

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
           AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p, new MultiTurnBlockPower(p, block, TURNS), TURNS));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
