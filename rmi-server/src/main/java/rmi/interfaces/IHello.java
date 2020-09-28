package rmi.interfaces;

import rmi.bean.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author liutianlong
 * @date 2020/9/29 0:11
 * @description 定义一个远程接口，必须继承Remote接口，其中需要远程调用的方法必须抛出RemoteException异常
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
