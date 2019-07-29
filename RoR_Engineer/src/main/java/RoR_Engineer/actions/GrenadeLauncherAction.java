package RoR_Engineer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;

//Todo: Adjust effects to be grenade-like

public class GrenadeLauncherAction extends AbstractGameAction {
    public int[] multiDamage;
    private boolean freeToPlayOnce = false;
    private DamageType damageType;
    private AbstractPlayer p;
    private int energyOnUse = -1;

    public GrenadeLauncherAction(AbstractPlayer p, int[] multiDamage, DamageType damageType, boolean freeToPlayOnce, int energyOnUse) {
        this.multiDamage = multiDamage;
        this.damageType = damageType;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (effect > 0) {

            AbstractDungeon.actionManager.addToBottom(new VFXAction(new HemokinesisEffect(p.hb.cX, p.hb.cY, (float) Settings.WIDTH * 0.65F, AbstractDungeon.floorY * 1.10F), 0.5F)); //this attack has no target, so just fling it to the right

            for(int i = 0; i < effect; ++i) {
/*
                if (i == 0) {
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_WHIRLWIND"));
                }
                AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(this.p, new CleaveEffect(), 0.0F));
*/

                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(this.p, this.multiDamage, this.damageType, AttackEffect.FIRE, true));
            }

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}
