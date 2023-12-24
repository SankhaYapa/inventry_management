package com.novig.agency_management_system.controller;

import com.novig.agency_management_system.dto.requestDto.RequestShopDto;
import com.novig.agency_management_system.dto.responseDto.ShopDTO;
import com.novig.agency_management_system.entity.Shop;
import com.novig.agency_management_system.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @PostMapping("/add-shop")
    public ResponseEntity<Shop> addShop(@RequestBody RequestShopDto requestShopDto) {

        Shop shop = shopService.addShop(requestShopDto);
        return ResponseEntity.ok(shop);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShopDTO>> getAllShop() {
        List<ShopDTO> shopList = shopService.getAllShop();
        return ResponseEntity.ok(shopList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteShop(@PathVariable Long id) {
        String status = shopService.deleteShop(id);

        return ResponseEntity.ok(status);

    }

    @PutMapping("/update")
    public ResponseEntity<Shop> updateShop(@RequestBody RequestShopDto requestShopDto) {
        Shop shop = shopService.updateShop(requestShopDto);
        return ResponseEntity.ok(shop);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable Long id) {
        Shop shop = shopService.getShopById(id);
        return ResponseEntity.ok(shop);
    }
    @GetMapping("/by-delivery-route/{deliveryRouteId}")
    public ResponseEntity<List<ShopDTO>> getShopsByDeliveryRouteId(@PathVariable Long deliveryRouteId) {
        List<ShopDTO> shopList = shopService.getShopsByDeliveryRouteId(deliveryRouteId);
        return ResponseEntity.ok(shopList);
    }

}
