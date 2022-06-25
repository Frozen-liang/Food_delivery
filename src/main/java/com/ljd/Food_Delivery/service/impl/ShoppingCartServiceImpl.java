package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.convert.ShoppingCarConverter;
import com.ljd.Food_Delivery.domain.entity.ShoppingCartEntity;
import com.ljd.Food_Delivery.domain.mapper.ShoppingCartMapper;
import com.ljd.Food_Delivery.dto.request.ShoppingCartRequest;
import com.ljd.Food_Delivery.exception.ErrorCode;
import com.ljd.Food_Delivery.exception.FoodException;
import com.ljd.Food_Delivery.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCartEntity>
        implements ShoppingCartService {

    private final ShoppingCarConverter shoppingCarConverter;

    public ShoppingCartServiceImpl(ShoppingCarConverter shoppingCarConverter) {
        this.shoppingCarConverter = shoppingCarConverter;
    }

    @Override
    public List<ShoppingCartEntity> getList() {
        try {
            LambdaQueryWrapper<ShoppingCartEntity> lqw = new LambdaQueryWrapper<>();
            lqw.orderByDesc(ShoppingCartEntity::getCreateTime);
            return list(lqw);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.SHOPPING_CART_LIST_ERROR);
        }
    }

    @Override
    public boolean add(ShoppingCartRequest request) {
        try {
            // 得到当前用户Id
            ShoppingCartEntity shoppingCart = shoppingCarConverter.toEntity(request);
            shoppingCart.setUserId(1L);

            // 判断添加的是菜品还是套餐
            LambdaQueryWrapper<ShoppingCartEntity> sclqw = new LambdaQueryWrapper<>();
            Long dishId = shoppingCart.getDishId();
            Long setmealId = shoppingCart.getSetmealId();

            if (dishId != null) {
                sclqw.eq(ShoppingCartEntity::getDishId, dishId);
            }

            if (setmealId != null) {
                sclqw.eq(ShoppingCartEntity::getSetmealId, setmealId);
            }

            // 判断购物车数量多少
            ShoppingCartEntity mount = getOne(sclqw);
            if (mount == null) {
                shoppingCart.setNumber(1);
                save(shoppingCart);
                // 把对象赋值给判断对象
                mount = shoppingCart;
            } else {
                int number = shoppingCart.getNumber();
                // 不生效
                shoppingCart.setNumber(++number);
                updateById(shoppingCart);
            }
            return true;
        } catch (Exception e) {
            throw new FoodException(ErrorCode.CATEGORY_ADD_ERROR);
        }
    }

    @Override
    public ShoppingCartEntity getEntityById(Long id) {
        return getById(id);
    }

    @Override
    public boolean delete() {
        try {
            LambdaQueryWrapper<ShoppingCartEntity> sclqw = new LambdaQueryWrapper<>();
            // 根据当前使用用户id删除getUserId
            sclqw.eq(ShoppingCartEntity::getUserId, 1);

            return remove(sclqw);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.DISH_DELETE_ERROR);
        }

    }
}
