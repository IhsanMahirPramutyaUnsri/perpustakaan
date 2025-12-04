// Modul: 1 (Variable, Array), 2 (Kondisi), 3 (Loop), 5 (Class, Inheritance), 6 (Encapsulation, Polymorphism)

import java.util.Scanner;
import java.util.ArrayList;

abstract class Item {
    private String id;
    private String title;
    private boolean isAvailable;
    
    public Item(String id, String title) {
        this.id = id;
        this.title = title;
        this.isAvailable = true;
    }
    
    public String getId() { return id; }
    public String getTitle() { return title; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    
    // Abstract method (Polymorphism)
    public abstract void displayInfo();
    public abstract String getType();
}

class Book extends Item {
    private String author;
    private int pages;
    
    public Book(String id, String title, String author, int pages) {
        super(id, title);
        this.author = author;
        this.pages = pages;
    }
    
    public String getAuthor() { return author; }
    public int getPages() { return pages; }
    
    @Override
    public void displayInfo() {
        System.out.println("  [BUKU] ID: " + getId());
        System.out.println("  Judul: " + getTitle());
        System.out.println("  Penulis: " + author);
        System.out.println("  Halaman: " + pages);
        System.out.println("  Status: " + (isAvailable() ? "Tersedia" : "Dipinjam"));
    }
    
    @Override
    public String getType() { return "Buku"; }
}

class Magazine extends Item {
    private String publisher;
    private int edition;
    
    public Magazine(String id, String title, String publisher, int edition) {
        super(id, title);
        this.publisher = publisher;
        this.edition = edition;
    }
    
    public String getPublisher() { return publisher; }
    public int getEdition() { return edition; }
    
    @Override
    public void displayInfo() {
        System.out.println("  [MAJALAH] ID: " + getId());
        System.out.println("  Judul: " + getTitle());
        System.out.println("  Penerbit: " + publisher);
        System.out.println("  Edisi: " + edition);
        System.out.println("  Status: " + (isAvailable() ? "Tersedia" : "Dipinjam"));
    }
    
    @Override
    public String getType() { return "Majalah"; }
}

class Member {
    private String memberId;
    private String name;
    private ArrayList<Item> borrowedItems;
    
    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }
    
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public ArrayList<Item> getBorrowedItems() { return borrowedItems; }
    
    public void borrowItem(Item item) {
        borrowedItems.add(item);
    }
    
    public void returnItem(Item item) {
        borrowedItems.remove(item);
    }
}

public class test {

    private static ArrayList<Item> items = new ArrayList<>();
    private static ArrayList<Member> members = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("  SISTEM MANAJEMEN PERPUSTAKAAN - KEL 47  ");
        
        initializeData();
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Pilihan Anda: ");

            switch (choice) {
                case 1:
                    displayAllItems();
                    break;
                case 2:
                    searchItem();
                    break;
                case 3:
                    borrowItem();
                    break;
                case 4:
                    returnItem();
                    break;
                case 5:
                    displayMembers();
                    break;
                case 6:
                    addNewItem();
                    break;
                case 0:
                    running = false;
                    System.out.println("\n✓ Terima kasih! Program selesai.");
                    System.out.println("  © Kelompok 47 - PROGDAS");
                    break;
                default:
                    System.out.println("\n✗ Pilihan tidak valid!");
            }
            
            if (running) {
                System.out.println("\nTekan Enter untuk melanjutkan...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void initializeData() {
        items.add(new Book("B001", "Pemrograman Java", "James Gosling", 450));
        items.add(new Book("B002", "Algoritma dan Struktur Data", "Thomas Cormen", 600));
        items.add(new Book("B003", "Database Management", "Raghu Ramakrishnan", 520));
        
        items.add(new Magazine("M001", "Tech Monthly", "TechPress", 45));
        items.add(new Magazine("M002", "Code Magazine", "DevPublisher", 12));
        
        members.add(new Member("MEM001", "Ihsan Mahir"));
        members.add(new Member("MEM002", "Ignatius Christoper Evan"));
        members.add(new Member("MEM003", "Ahmad WIldan"));
        members.add(new Member("MEM004", "Widia Santika"));
    }
    
    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("              MENU PERPUSTAKAAN - KEL 47");
        System.out.println("=".repeat(50));
        System.out.println("  1. Tampilkan Semua Item");
        System.out.println("  2. Cari Item");
        System.out.println("  3. Pinjam Item");
        System.out.println("  4. Kembalikan Item");
        System.out.println("  5. Lihat Daftar Member");
        System.out.println("  6. Tambah Item Baru");
        System.out.println("  0. Keluar");
        System.out.println("=".repeat(50));
    }
    
    private static void displayAllItems() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           DAFTAR SEMUA ITEM PERPUSTAKAAN");
        System.out.println("=".repeat(50));
        
        if (items.isEmpty()) {
            System.out.println("  Tidak ada item dalam perpustakaan.");
            return;
        }
        
        for (int i = 0; i < items.size(); i++) {
            System.out.println("\n[" + (i + 1) + "]");
            items.get(i).displayInfo(); 
        }
        
        int availableCount = 0;
        for (Item item : items) {
            if (item.isAvailable()) {
                availableCount++;
            }
        }
        
        System.out.println("\n" + "-".repeat(50));
        System.out.println("Total Item: " + items.size());
        System.out.println("Tersedia: " + availableCount);
        System.out.println("Dipinjam: " + (items.size() - availableCount));
    }
    
    private static void searchItem() {
        System.out.println("\n=== PENCARIAN ITEM ===");
        System.out.print("Masukkan kata kunci (judul/ID): ");
        String keyword = scanner.nextLine().toLowerCase();
        
        boolean found = false;
        int count = 0;
        
        for (Item item : items) {
            if (item.getTitle().toLowerCase().contains(keyword) || 
                item.getId().toLowerCase().contains(keyword)) {
                if (!found) {
                    System.out.println("\nHasil Pencarian:");
                    found = true;
                }
                count++;
                System.out.println("\n[" + count + "]");
                item.displayInfo();
            }
        }
        
        if (!found) {
            System.out.println("✗ Tidak ada item yang cocok dengan kata kunci.");
        } else {
            System.out.println("\n✓ Ditemukan " + count + " item.");
        }
    }
    
    private static void borrowItem() {
        System.out.println("\n=== PEMINJAMAN ITEM ===");
        
        displayMemberList();
        int memberIndex = getIntInput("Pilih nomor member: ") - 1;
        
        if (memberIndex < 0 || memberIndex >= members.size()) {
            System.out.println("✗ Member tidak valid!");
            return;
        }
        
        Member member = members.get(memberIndex);
        
        displayAvailableItems();
        int itemIndex = getIntInput("Pilih nomor item: ") - 1;
        
        if (itemIndex < 0 || itemIndex >= items.size()) {
            System.out.println("✗ Item tidak valid!");
            return;
        }
        
        Item item = items.get(itemIndex);
        
        if (!item.isAvailable()) {
            System.out.println("✗ Item sedang dipinjam!");
        } else {
            item.setAvailable(false);
            member.borrowItem(item);
            System.out.println("✓ Peminjaman berhasil!");
            System.out.println("  Member: " + member.getName());
            System.out.println("  Item: " + item.getTitle());
        }
    }
    
    private static void returnItem() {
        System.out.println("\n=== PENGEMBALIAN ITEM ===");
        
        displayMemberList();
        int memberIndex = getIntInput("Pilih nomor member: ") - 1;
        
        if (memberIndex < 0 || memberIndex >= members.size()) {
            System.out.println("✗ Member tidak valid!");
            return;
        }
        
        Member member = members.get(memberIndex);
        
        if (member.getBorrowedItems().isEmpty()) {
            System.out.println("✗ Member tidak memiliki pinjaman.");
            return;
        }
        
        System.out.println("\nItem yang dipinjam:");
        for (int i = 0; i < member.getBorrowedItems().size(); i++) {
            System.out.println((i + 1) + ". " + member.getBorrowedItems().get(i).getTitle());
        }
        
        int itemIndex = getIntInput("Pilih nomor item yang dikembalikan: ") - 1;
        
        if (itemIndex < 0 || itemIndex >= member.getBorrowedItems().size()) {
            System.out.println("✗ Pilihan tidak valid!");
            return;
        }
        
        Item item = member.getBorrowedItems().get(itemIndex);
        item.setAvailable(true);
        member.returnItem(item);
        System.out.println("✓ Pengembalian berhasil!");
    }
    
    private static void displayMembers() {
        System.out.println("\n=== DAFTAR MEMBER ===");
        
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            System.out.println("\n[" + (i + 1) + "] " + member.getName());
            System.out.println("    ID: " + member.getMemberId());
            System.out.println("    Jumlah Pinjaman: " + member.getBorrowedItems().size());
            
            if (!member.getBorrowedItems().isEmpty()) {
                System.out.println("    Item yang dipinjam:");
                for (Item item : member.getBorrowedItems()) {
                    System.out.println("    - " + item.getTitle());
                }
            }
        }
    }
    
    private static void addNewItem() {
        System.out.println("\n=== TAMBAH ITEM BARU ===");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Tambah Majalah");
        int choice = getIntInput("Pilihan: ");
        
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Judul: ");
        String title = scanner.nextLine();
        
        switch (choice) {
            case 1:
                System.out.print("Penulis: ");
                String author = scanner.nextLine();
                int pages = getIntInput("Jumlah Halaman: ");
                items.add(new Book(id, title, author, pages));
                System.out.println("✓ Buku berhasil ditambahkan!");
                break;
            case 2:
                System.out.print("Penerbit: ");
                String publisher = scanner.nextLine();
                int edition = getIntInput("Edisi: ");
                items.add(new Magazine(id, title, publisher, edition));
                System.out.println("✓ Majalah berhasil ditambahkan!");
                break;
            default:
                System.out.println("✗ Pilihan tidak valid!");
        }
    }
    
    private static void displayMemberList() {
        System.out.println("\nDaftar Member:");
        for (int i = 0; i < members.size(); i++) {
            System.out.println((i + 1) + ". " + members.get(i).getName() + 
                             " (" + members.get(i).getMemberId() + ")");
        }
    }
    
    private static void displayAvailableItems() {
        System.out.println("\nItem yang Tersedia:");
        int count = 1;
        boolean hasAvailable = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isAvailable()) {
                System.out.println(count + ". [" + (i + 1) + "] " + items.get(i).getTitle() + 
                                 " [" + items.get(i).getType() + "]");
                count++;
                hasAvailable = true;
            }
        }
        if (!hasAvailable) {
            System.out.println("✗ Tidak ada item yang tersedia.");
        }
    }
    
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Input harus angka! " + prompt);
        }
        int value = scanner.nextInt();
        scanner.nextLine(); 
        return value;
    }
}