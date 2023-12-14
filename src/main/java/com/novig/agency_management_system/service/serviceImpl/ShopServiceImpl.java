package com.novig.agency_management_system.service.serviceImpl;


import com.novig.agency_management_system.dto.requestDto.RequestShopDto;
import com.novig.agency_management_system.entity.Shop;
import com.novig.agency_management_system.repository.ShopRepo;
import com.novig.agency_management_system.service.ShopService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {
    
    @Autowired
    private ShopRepo shopRepo;
    
    @Override
    public Shop addShop(RequestShopDto requestShopDto) {
        try {
            Shop shop = new Shop(
                    requestShopDto.getShopId(),
                    requestShopDto.getShopName(),
                    requestShopDto.getAddress(),
                    requestShopDto.getPhoneNumber()
            );
            shopRepo.save(shop);
            return shop;
        } catch (Exception e) {

            throw new RuntimeException("Error saving shop: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Shop> getAllShop() {
        List<Shop> shopList = shopRepo.findAll();
        return shopList;
    }

    @Override
    public String deleteShop(Long id) {
        shopRepo.deleteById(id);
        return "Deleted !!";
    }

    @Override
    public Shop updateShop(RequestShopDto requestShopDto) {
        try {
            Optional<Shop> shopOptional = shopRepo.findById(requestShopDto.getShopId());

            if (shopOptional.isPresent()) {
                Shop shop = shopOptional.get();
                shop.setShopName(requestShopDto.getShopName());
                shop.setAddress(requestShopDto.getAddress());
                shop.setPhoneNumber(requestShopDto.getPhoneNumber());


                return shopRepo.save(shop);
            } else {
                throw new EntityNotFoundException("Shop not found with id: " + requestShopDto.getShopId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating shop", e);
        }
    }
}

