package com.beyondrealitygaming.core.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BRUnbreakeableBlock extends BRBlock {

    public static final PropertyInteger TYPE = PropertyInteger.create("type",0,15);

    public BRUnbreakeableBlock(String name) {
        super(Material.ROCK,name, CreativeTabs.BUILDING_BLOCKS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 0));
        setBlockUnbreakable();
        setHardness(6000000000F);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(TYPE);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this,  new IProperty[] {TYPE});
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
        for (int x : TYPE.getAllowedValues()) {
            list.add(new ItemStack(itemIn, 1, x));
        }
    }

    public void registerModels(){
        for (int x : TYPE.getAllowedValues()){
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), x, new ModelResourceLocation(new ResourceLocation(this.getRegistryName().toString().toLowerCase()), "normal"));
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), x, new ModelResourceLocation(new ResourceLocation(this.getRegistryName().toString().toLowerCase()), "inventory"));
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        worldIn.setBlockState(pos,state.withProperty(TYPE,stack.getMetadata()));
    }

}
