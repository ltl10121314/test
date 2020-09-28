package rmi.interfaces;

import rmi.bean.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author liutianlong
 * @date 2020/9/29 0:10
 * @description 远程的接口的实现，继承了UnicastRemoteObject，表明该类作为一个远程对象
 */
public class HelloImpl extends UnicastRemoteObject implements IHello {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 因为UnicastRemoteObject的构造方法抛出了RemoteException异常，因此这里默认的构造方法必须写，必须声明抛出RemoteException异常
     *
     * @throws RemoteException
     */
    public HelloImpl() throws RemoteException {
    }

    @Override
    public User updateUser(User user) throws RemoteException {
        System.out.println("-------------- 客户端发送的user为" + user.toString());
        user.setName("andy2");
        user.setAge(30);
        return user;
    }
}
