import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Acomodacao {
    private int roomId;
    private int hostId;
    private String roomType;
    private String country;
    private String city;
    private String neighbourhood;
    private int reviews;
    private double overallSatisfaction;
    private int accommodates;
    private double bedrooms;
    private double price;
    private String propertyType;

    public Acomodacao() {}

    public Acomodacao(int roomId, int hostId, String roomType, String country, String city, String neighbourhood,
                      int reviews, double overallSatisfaction, int accommodates, double bedrooms, double price,
                      String propertyType) {
        this.roomId = roomId;
        this.hostId = hostId;
        this.roomType = roomType;
        this.country = country;
        this.city = city;
        this.neighbourhood = neighbourhood;
        this.reviews = reviews;
        this.overallSatisfaction = overallSatisfaction;
        this.accommodates = accommodates;
        this.bedrooms = bedrooms;
        this.price = price;
        this.propertyType = propertyType;
    }

    // Métodos gets
    public int getRoomId() {
        return roomId;
    }

    public int getHostId() {
        return hostId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public int getReviews() {
        return reviews;
    }

    public double getOverallSatisfaction() {
        return overallSatisfaction;
    }

    public int getAccommodates() {
        return accommodates;
    }

    public double getBedrooms() {
        return bedrooms;
    }

    public double getPrice() {
        return price;
    }

    public String getPropertyType() {
        return propertyType;
    }

    // Métodos sets
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public void setOverallSatisfaction(double overallSatisfaction) {
        this.overallSatisfaction = overallSatisfaction;
    }

    public void setAccommodates(int accommodates) {
        this.accommodates = accommodates;
    }

    public void setBedrooms(double bedrooms) {
        this.bedrooms = bedrooms;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    // Método clone
    public Acomodacao clone() {
        return new Acomodacao(roomId, hostId, roomType, country, city, neighbourhood, reviews, overallSatisfaction,
                accommodates, bedrooms, price, propertyType);
    }

    public void ler() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite os dados da acomodação:");
        System.out.print("roomId: ");
        roomId = scanner.nextInt();
        System.out.print("hostId: ");
        hostId = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("roomType: ");
        roomType = scanner.nextLine();
        System.out.print("country: ");
        country = scanner.nextLine();
        System.out.print("city: ");
        city = scanner.nextLine();
        System.out.print("neighbourhood: ");
        neighbourhood = scanner.nextLine();
        System.out.print("reviews: ");
        reviews = scanner.nextInt();
        System.out.print("overallSatisfaction: ");
        overallSatisfaction = scanner.nextDouble();
        System.out.print("accommodates: ");
        accommodates = scanner.nextInt();
        System.out.print("bedrooms: ");
        bedrooms = scanner.nextDouble();
        System.out.print("price: ");
        price = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("propertyType: ");
        propertyType = scanner.nextLine();
    }


    public void imprimir() {
        System.out.println("[" + roomId + " ## " + hostId + " ## " + roomType + " ## " +
                country + " ## " + city + " ## " + neighbourhood + " ## " + reviews +
                " ## " + overallSatisfaction + " ## " + accommodates + " ## " +
                bedrooms + " ## " + price + " ## " + propertyType + "]");
    }
}

public class Main {
    public static void main(String[] args) {
        Acomodacao[] acomodacoes = lerArquivo("/tmp/dados_airbnb.txt");


        processarEntrada(acomodacoes);
    }

    public static Acomodacao[] lerArquivo(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));

            scanner.nextLine();

            int count = 0;
            while (scanner.hasNextLine()) {
                count++;
                scanner.nextLine();
            }

            scanner = new Scanner(new File(fileName));
            scanner.nextLine();

            Acomodacao[] acomodacoes = new Acomodacao[count];
            int index = 0;
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("\t");
                acomodacoes[index] = new Acomodacao(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2],
                        data[3], data[4], data[5], Integer.parseInt(data[6]), Double.parseDouble(data[7]),
                        Integer.parseInt(data[8]), Double.parseDouble(data[9]), Double.parseDouble(data[10]),
                        data[11]);
                index++;
            }
            return acomodacoes;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + fileName);
            return null;
        }
    }

    public static void processarEntrada(Acomodacao[] acomodacoes) {
        Scanner scanner = new Scanner(System.in);

        String id = scanner.nextLine();
        while (!id.equals("FIM")) {
            int idBusca = Integer.parseInt(id);
            for (Acomodacao acomodacao : acomodacoes) {
                if (acomodacao.getRoomId() == idBusca) {
                    acomodacao.imprimir();
                    break;
                }
            }

            id = scanner.nextLine();
        }
    }
}
