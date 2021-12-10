// ISum.aidl
package com.aslam.aidl_server;

import com.aslam.aidl_server.User;

interface ISum {
    int Sum(int a, int b);
    User getUser(int num);
}