package com.nisie.udacitybakingapp.main.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author by nisie on 6/27/2017.
 */

public class BaseMapper {
    protected Gson gson = new GsonBuilder().disableHtmlEscaping()
            .setPrettyPrinting().create();
}
