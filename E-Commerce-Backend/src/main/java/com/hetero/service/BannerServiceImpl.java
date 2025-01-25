package com.hetero.service;

import com.hetero.models.Banner;
import com.hetero.repository.BannerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerRepository;

    @Override
    public Banner createBanner(Banner banner) {
        return bannerRepository.save(banner);
    }

    @Override
    public Banner updateBanner(int id, Banner banner) {
        Banner existingBanner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner not found with id: " + id));

        existingBanner.setImageUrl(banner.getImageUrl());
        existingBanner.setActive(banner.getActive());
        existingBanner.setTargetScreen(banner.getTargetScreen());

        return bannerRepository.save(existingBanner);
    }

    @Override
    public void deleteBanner(int id) {
        if (!bannerRepository.existsById(id)) {
            throw new RuntimeException("Banner not found with id: " + id);
        }
        bannerRepository.deleteById(id);
    }

    @Override
    public Banner getBannerById(int id) {
        return bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner not found with id: " + id));
    }

    @Override
    public List<Banner> getAllBanners() {
        return bannerRepository.findAll();
    }
}

