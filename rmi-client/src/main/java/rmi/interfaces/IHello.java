package rmi.interfaces;

import rmi.bean.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author liutianlong
 * @date 2020/9/29 0:23
 * @description
 */
public interface IHello extends Remote {

    /**
     * 更新user信息
     * @param user
     * @return
     * @throws RemoteException
     */
    public User updateUser(User user) throws RemoteException;
}
