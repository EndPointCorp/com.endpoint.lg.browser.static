/*
 * Copyright (C) 2015 End Point Corporation
 * Copyright (C) 2014 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.endpoint.lg.browser;

import com.endpoint.lg.support.window.WindowIdentity;
import com.endpoint.lg.support.window.WindowInstanceIdentity;
import com.endpoint.lg.support.window.ManagedWindow;

import interactivespaces.activity.impl.ros.BaseRoutableRosActivity;
import interactivespaces.activity.component.web.BasicWebBrowserActivityComponent;
import interactivespaces.activity.component.web.WebBrowserActivityComponent;

import java.io.File;

/**
 * An Interactive Spaces activity which launches a Web Browser
 * and implementes limited instrumentation of the browser instance.
 *
 * @author Kiel Christofferson <kiel@endpoint.com>
 */
public class LGBrowserActivity extends BaseRoutableRosActivity {

    /**
     * Browser component for the activitiy.
     */
    private WebBrowserActivityComponent browserComponent;

    /**
     * WindowIdentity for our browser window.
     */
    private WindowIdentity windowId;

    /**
     * ManagedWindow for our browser window.
     */
    private ManagedWindow window;

    @Override
    public void onActivitySetup() {
        final File tmpdir = getActivityFilesystem().getTempDataDirectory();
        windowId = new WindowInstanceIdentity(tmpdir.getAbsolutePath());
        window = new ManagedWindow(this, windowId);
        addManagedResource(window);

        browserComponent = new BasicWebBrowserActivityComponent();
        addActivityComponent(browserComponent);

        getLog().info("Activity com.endpoint.lg.browser setup");
    }

    @Override
    public void onActivityStartup() {
        getLog().info("Activity com.endpoint.lg.browser startup");
    }

    @Override
    public void onActivityActivate() {
        window.setVisible(true);
    }

    @Override
    public void onActivityDeactivate() {
        window.setVisible(false);
    }
}
