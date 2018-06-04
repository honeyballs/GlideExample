package de.thm.glideexample;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by Yannick Bals on 04.06.2018.
 */

// Glide will generate an API from an annotated class. The class can be empty, the default API will be generated.
// You are able to provide additional functionality by adding GlideExtensions ore overriding Methods.
@GlideModule
public class MyGlideAppModule extends AppGlideModule {}
