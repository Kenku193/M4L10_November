package org.example;

import org.example.model.Device;
import org.example.model.Port;
import org.example.session_provider.PropertiesSessionProvider;
import org.example.session_provider.SessionProvider;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String maxBandwithFromUser = "2";
        String maxConnectionsFromUser = "2";

        String sql = "select device from Port where device.maxConnection = " + maxConnectionsFromUser + " and bandwith = " + maxBandwithFromUser;

        SessionProvider sessionProvider = new PropertiesSessionProvider();
        SessionFactory sessionFactory = sessionProvider.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {

            // ПРОСТОЙ HQL Query ОБЪЕКТ

            {
                Query<Device> query = session.createQuery("from Device", Device.class);

                List<Device> list = query.list();
                for (Device device : list
                ) {
                    System.out.println(device);
                }

                Device device = query.getSingleResult();
                System.out.println(device);

            }
            // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

            // ПАРАМЕТРИЗОВАННЫЙ HQL Query ОБЪЕКТ

            {
                Query<Device> queryWithParams = session.createQuery("select device from Port where device.maxConnections= :param1 and bandwidth = :param2", Device.class);
                queryWithParams.setParameter("param1", scanner.nextInt());
                queryWithParams.setParameter("param2", scanner.nextInt());
            }

            // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

            // ПАРАМЕТРИЗОВАННЫЙ СПИСКОМ HQL Query ОБЪЕКТ

            {
                Query<Device> queryWithListedParams = session.createQuery("select device from Port where bandwidth in (:list)", Device.class);

                queryWithListedParams.setParameterList("list", List.of(3, 4));

                queryWithListedParams.setFirstResult(1).setMaxResults(1);

                List<Device> list = queryWithListedParams.list();
                for (Device device : list) {
                    System.out.println(device);
                }
            }

            // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

            // ПРИМЕР С ФУНКЦИЕЙ current_date()

            {
                Query<Device> query = session.createQuery("from Device where installationDate = current_date()", Device.class);

            }

            // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

            // ПРИМЕР С ВЫБОРКОЙ СУЩНОСТИ СО ВЛОЖЕННОЙ СУЩНОСТЬЮ

            {
                Query<Port> queryForPortOne = session.createQuery("from Port", Port.class);

                List<Port> list = queryForPortOne.list();
                for (Port port : list) {
                    System.out.println(port);
                }
            }

            {
                Query<Port> queryForPortAnother = session.createQuery("from Port where device.maxConnections = 2", Port.class);

                List<Port> list = queryForPortAnother.list();
                for (Port port : list) {
                    System.out.println(port);
                }
            }
            // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

            // ПРИМЕР РАБОТЫ СО Scroll
            {
                ScrollableResults<Device> scroll = query.scroll();
                scroll.scroll(5);
                while (scroll.next()) {
                    Device device = scroll.get();
                    System.out.println(device);
                }
            }

            // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

            // ИСПОЛЬЗОВАНИЕ Named Query
            {
                Query<Device> deviceWithInstallationDateMoreThen1Year = session.createNamedQuery("DeviceWithInstallationDateMoreThen1Year", Device.class);

                List<Device> list = deviceWithInstallationDateMoreThen1Year.list();
                for (Device device : list) {
                    System.out.println(device);
                }
            }
        }

    }
}