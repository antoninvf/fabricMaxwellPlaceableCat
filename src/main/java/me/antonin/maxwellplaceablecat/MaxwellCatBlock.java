package me.antonin.maxwellplaceablecat;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;

public class MaxwellCatBlock extends FallingBlock {
    public MaxwellCatBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.addParticle(ParticleTypes.HEART, pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5, 0, 0.5, 0);
        if (!world.isClient) {
            world.playSound(null, pos, SoundEvents.ENTITY_CAT_AMBIENT, SoundCategory.BLOCKS, 1, new Random().nextFloat() * (1.2f - 0.8f) + 0.8f);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public DamageSource getDamageSource() {
        return new DamageSource("falling_maxwell_cat") {
            @Override
            public Text getDeathMessage(LivingEntity livingEntity) {
                return Text.translatable("death.maxwellplaceablecat.falling_dingus_cat", livingEntity.getDisplayName());
            }
        }.setFromFalling();
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if (!world.isClient) {
            world.playSound(null, pos, SoundEvents.ENTITY_CAT_AMBIENT, SoundCategory.BLOCKS, 1, new Random().nextFloat() * (1.2f - 0.8f) + 0.8f);
        }
    }

    @Override
    protected void configureFallingBlockEntity(FallingBlockEntity fallingBlockEntity) {
        // a little bit of trolling
        fallingBlockEntity.setHurtEntities(5000, 5000);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing());
    }
}