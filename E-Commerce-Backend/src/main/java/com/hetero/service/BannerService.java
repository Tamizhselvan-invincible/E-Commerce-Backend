package com.hetero.service;

import com.hetero.models.Banner;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BannerService {
    Banner createBanner(Banner banner);

    Banner updateBanner(int id, Banner banner);

    void deleteBanner(int id);

    Banner getBannerById(int id);

    List<Banner> getAllBanners();
}

