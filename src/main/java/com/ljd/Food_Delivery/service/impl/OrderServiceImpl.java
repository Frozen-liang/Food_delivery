package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.domain.entity.*;
import com.ljd.Food_Delivery.domain.mapper.OrderMapper;
import com.ljd.Food_Delivery.exception.ErrorCode;
import com.ljd.Food_Delivery.exception.FoodException;
import com.ljd.Food_Delivery.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrdersEntity> implements
        OrderService {

    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final AddressBookService addressBookService;
    @Autowired
    private final OrderDetailService orderDetailService;

    public OrderServiceImpl(UserService userService, ShoppingCartService shoppingCartService, AddressBookService addressBookService, OrderDetailService orderDetailService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.addressBookService = addressBookService;
        this.orderDetailService = orderDetailService;
    }

    @Override
    public Page<OrdersEntity> getOrderByPage(int page, int pageSize, String number) {
        try {
            // 创建分页构造器
            Page<OrdersEntity> ordersEntityPage = new Page<>(page, pageSize);
            LambdaQueryWrapper<OrdersEntity> olqw = new LambdaQueryWrapper<>();
            // 三个参数 判断是否为空 列名 查询的值
            olqw.like(StringUtils.isNotEmpty(number), OrdersEntity::getNumber, number);
            // 降序排序
            olqw.orderByDesc(OrdersEntity::getOrderTime);
            // 分页查询
            return page(ordersEntityPage, olqw);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.EMPLOYEE_PAGE_ERROR);
        }
    }

    @Override
    public Page<OrdersEntity> getUserByPage(int page, int pageSize) {
        try {
            // 创建分页构造器
            Page<OrdersEntity> userEntityPage = new Page<>(page, pageSize);
            LambdaQueryWrapper<OrdersEntity> olqw = new LambdaQueryWrapper<>();
            // 降序排序
            olqw.orderByDesc(OrdersEntity::getOrderTime);
            // 分页查询
            return page(userEntityPage, olqw);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.EMPLOYEE_PAGE_ERROR);
        }
    }

    @Override
    public boolean submit(OrdersEntity orders) {
        //查询当前用户购物车数据
        LambdaQueryWrapper<ShoppingCartEntity> slqw = new LambdaQueryWrapper<>();
        slqw.eq(ShoppingCartEntity::getUserId,1);
        List<ShoppingCartEntity> shoppingCartEntityList = shoppingCartService.list(slqw);

        // 判断购物车是否为空
        if (shoppingCartEntityList == null) {
            throw new FoodException(ErrorCode.CATEGORY_PAGE_ERROR);
        }

        // 查询用户信息
        UserEntity user = userService.getById(1);
        // 查询地址信息
        long addressBookId = orders.getAddressBookId();
        AddressBookEntity addressBook = addressBookService.getById(addressBookId);
        if (addressBook == null) {
            throw new FoodException(ErrorCode.CATEGORY_PAGE_ERROR);
        }

        //购物特定生成订单号
        long orderId = IdWorker.getId();
        // BigDecimal
        AtomicInteger amount = new AtomicInteger(0);

        // 组装订单细明
        List<OrderDetailEntity> orderDetails = shoppingCartEntityList.stream().map((item) -> {
            OrderDetailEntity orderDetail = new OrderDetailEntity();
            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(item.getNumber());
            orderDetail.setDishFlavor(item.getDishFlavor());
            orderDetail.setDishId(item.getDishId());
            orderDetail.setSetmealId(item.getSetmealId());
            orderDetail.setName(item.getName());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getAmount());
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());


        // 保存订单数据
        orders.setId(orderId);
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setAmount(new BigDecimal(amount.get()));//总金额
        orders.setUserId(1);
        orders.setNumber(String.valueOf(orderId));
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                + (addressBook.getDetail() == null ? "" : addressBook.getDetail()));
        //向订单表插入数据，一条数据
        this.save(orders);

        //向订单明细表插入数据，多条数据
        orderDetailService.saveBatch(orderDetails);

        //清空购物车数据
        shoppingCartService.remove(slqw);

        return true;
    }

}
