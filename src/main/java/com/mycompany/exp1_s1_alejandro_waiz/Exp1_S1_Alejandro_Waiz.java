/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 package com.mycompany.exp1_s1_alejandro_waiz;

 import java.util.Scanner;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 /**
  *
  * @author Aleja
  */
 
 class Cliente {
 
     private String Rut;
     private String Nombre;
     private String Domicilio;
     private String Telefono;
     private CuentaCorriente CuentaCorriente;
 
     public Cliente (String Rut, String Nombre, String Domicilio, String Telefono, CuentaCorriente CuentaCorriente) {
         this.Rut = Rut;
         this.Nombre = Nombre;
         this.Domicilio = Domicilio;
         this.Telefono = Telefono;
         this.CuentaCorriente = CuentaCorriente;
     }
 
     public void MostrarDetalles(){
         System.out.println("\nNombre: " + Nombre);
         System.out.println("Rut: " + Rut);
         System.out.println("Domicilio: " + Domicilio);
         System.out.println("Telefono: " + Telefono);
         System.out.println("Cuenta corriente: " + CuentaCorriente.getNumeroCuenta());
         System.out.println("Saldo:$" + CuentaCorriente.getSaldo());
     }
 
     public CuentaCorriente getCuentaCorriente(){
         return this.CuentaCorriente;
     }
 
     public void Depositar(long Deposito){
         this.CuentaCorriente.setSaldo(this.CuentaCorriente.getSaldo() + Deposito);
     }
 
     public void Girar(long Giro){
         this.CuentaCorriente.setSaldo(this.CuentaCorriente.getSaldo() - Giro);
     }
 
  }
 
  class CuentaCorriente {
 
     private String NumeroCuenta; 
     private long Saldo;
 
     public CuentaCorriente(String NumeroCuenta){
         this.NumeroCuenta = NumeroCuenta;
         this.Saldo = 0;
     }
 
     public String getNumeroCuenta() {
         return NumeroCuenta;
     }
 
     public long getSaldo() {
         return Saldo;
     }
 
     public void setSaldo(long Saldo){
         this.Saldo = Saldo;
     }
 
  }
 
 public class Exp1_S1_Alejandro_Waiz {
 
     public static void main(String[] args) {
 
         Scanner scanner = new Scanner(System.in);
 
         System.out.println("Bienvenido a Bank Boston");
         System.out.println("Para usar nuestra aplicación primero debe crearse una cuenta\n");
 
         Cliente Cliente = null;
 
         while (true) {
             System.out.println("\nMenú:");
             System.out.println("1 - Registrarse como cliente.");
             System.out.println("2 - Ver tus datos.");
             System.out.println("3 - Depositar.");
             System.out.println("4 - Girar.");
             System.out.println("5 - Consultar saldo.");
             System.out.println("6 - Salir.");
             System.out.print("\nSelecciona una opción:\n ");
             int opcion = scanner.nextInt();
             scanner.nextLine();  // Consumir el salto de línea
 
             switch (opcion) {
                 case 1:
 
                     if (Cliente == null){
                         Cliente = CrearCliente(scanner);
                         break;
                     } else {
                         System.out.println("Ya tienes un cliente creado.\n");
                         break;
                     }
                    
                 case 2:
                     if (Cliente == null){
                         System.out.println("Aun no tienes un cliente creado\n");
                         break;
                     } else {
                         Cliente.MostrarDetalles();
                         break;
                     }
 
                 case 3:
                     if (Cliente == null){
                         System.out.println("Aun no tienes un cliente creado\n");
                         break;
                     } else {
                         Depositar(Cliente, scanner);
                         break;
                     }
                    
                 case 4:
                     if (Cliente == null){
                         System.out.println("Aun no tienes un cliente creado\n");
                         break;
                     } else {
                         Girar(Cliente, scanner);
                         break;
                     }
                     
                 case 5:
                     if (Cliente == null){
                         System.out.println("Aún no tienes un cliente creado\n");
                         break;
                     } else {
                         System.out.println("Tu saldo actual es de $" + Cliente.getCuentaCorriente().getSaldo());
                         break;
                     }
 
                 case 6:
                     if (Cliente == null){
                         System.out.println("Que tengas un buen dia!");
                         return;
                     } else {
                         System.out.println("Tu cuenta cierra con un saldo de $" + Cliente.getCuentaCorriente().getSaldo() + ". Que tengas un buen dia!" );
                         return;
                     }
                 
                 default:
                     System.out.println("Elige una opción válida");
             }
 
         }
 
 
     }
 
     private static Cliente CrearCliente(Scanner scanner){
         
         //Validaciones
         String REGEX_RUT = "^[0-9]{1,2}\\.[0-9]{3}\\.[0-9]{3}-[0-9kK]$";
         String REGEX_NumeroCuentaCorriente = "^[0-9]+$";
         String REGEX_Telefono = "^[0-9]{8}$";
         boolean ClienteValido = false;
 
         //DatosCliente
         Cliente Cliente = null;
         CuentaCorriente CuentaCorriente;
         String Domicilio;
         String Rut;
         String Telefono;
         String Nombre;
         String NumeroCuentaCorriente;
 
         do { 
 
         System.out.println("Ingrese su nombre...");
         Nombre = scanner.next();
 
         System.out.println("Ingrese su rut (con puntos y guión)...");
         Rut = scanner.next();
 
         Pattern Rutpattern = Pattern.compile(REGEX_RUT);
         Matcher Rutmatcher = Rutpattern.matcher(Rut);
 
         if (!Rutmatcher.matches()){
             System.out.println("Rut inválido, vuelve a intentarlo");    
             continue;       
         }
 
         System.out.println("Ingrese su domicilio...");
         Domicilio = scanner.next();
 
         System.out.println("Ingrese su telefono (sin el +569)...");
         Telefono = scanner.next();
 
         Pattern TelefonoPattern = Pattern.compile(REGEX_Telefono);
         Matcher TelefonoMatcher = TelefonoPattern.matcher(Telefono);
 
         if (!TelefonoMatcher.matches()){
             System.out.println("Número invalido, intentalo de nuevo");
             continue;
         }
 
         System.out.println("Ingrese su numero de cuenta corriente deseado (solo numeros y hasta 15 de ellos)...");
         NumeroCuentaCorriente = scanner.next();
 
         Pattern CuentaCorrientePattern = Pattern.compile(REGEX_NumeroCuentaCorriente);
         Matcher CuentaCorrienteMatcher = CuentaCorrientePattern.matcher(NumeroCuentaCorriente);
 
         if (!CuentaCorrienteMatcher.matches()){
             System.out.println("La cuenta corriente solo puede poseer números, intentalo de nuevo");
             continue;
         } else if (NumeroCuentaCorriente.length() > 15) {
             System.out.println("Esos son mas de 15 números, vuelve a intentarlo");    
             continue;   
         }
 
         CuentaCorriente = new CuentaCorriente(NumeroCuentaCorriente);
         Cliente = new Cliente(Rut, Nombre, Domicilio, "+569" + Telefono, CuentaCorriente);
 
         ClienteValido = true;
 
         } while (ClienteValido == false);
 
         return Cliente;
         
     }
 
     private static void Depositar(Cliente cliente, Scanner scanner) {
         boolean depositoValido = false;
         long deposito = 0;
     
         while (!depositoValido) {
             System.out.println("¿Cuánto dinero deseas depositar?");
     
             if (scanner.hasNextLong()) {
                 deposito = scanner.nextLong();
                 if (deposito <= 0) {
                     System.out.println("Depósito inválido, debe ser mayor a 0");
                 } else {
                     cliente.Depositar(deposito);
                     System.out.println("$" + deposito + " depositados exitosamente!");
                     depositoValido = true;
                 }
             } else {
                 System.out.println("Entrada inválida. Por favor, ingrese solo números.");
                 scanner.next(); 
             }
             scanner.nextLine(); 
         }
     }
     
     private static void Girar(Cliente cliente, Scanner scanner) {
         boolean giroValido = false;
         long giro = 0;
     
         while (!giroValido) {
             System.out.println("¿Cuánto dinero deseas girar?");
     
             if (scanner.hasNextLong()) {
                 giro = scanner.nextLong();
                 if (giro <= 0) {
                     System.out.println("Giro inválido, debe ser mayor a 0");
                 } else if (cliente.getCuentaCorriente().getSaldo() < giro) {
                     System.out.println("Giro inválido, estás intentando girar $" + giro + " y solo tienes $" + cliente.getCuentaCorriente().getSaldo() + " en la cuenta.");
                 } else {
                     cliente.Girar(giro);
                     System.out.println("$" + giro + " girados exitosamente!");
                     giroValido = true;
                 }
             } else {
                 System.out.println("Entrada inválida. Por favor, ingrese solo números.");
                 scanner.next();
             }
             scanner.nextLine(); 
         }
     }
     
     
 }
 