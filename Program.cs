using System;
//using System.Collections.Generic;
//using System.Text;
using WMILibraryNET40;
using System.IO;
using System.Threading;

namespace Test1Method
{
    public class Program
    {
        
        public static int testigo = 0;
      
         public int addInt(int a, int b, int c)
        {
            //testigo++;
            return a + b + c;
        }

        public int testAddInt(int a, int b, int c)
        {
            //System.Console.WriteLine("Testigo {0}" + testigo);
            return a + b + c;
        }

        public void addIntVoid(int a, int b, int c)
        {
            testigo = a + b + c;
        }

        public void testAddIntVoid(int a, int b, int c)
        {
            testigo = a + b + c;
        }
       


        int value1;
        int value2;
        int value3;

        public Program()
        {
            this.value1 = 0;
            this.value2 = 0;
            this.value3 = 0;
        }

        public Program(int value1, int value2, int value3)
        {
            
            this.value1 = value1;
            this.value2 = value2;
            this.value3 = value3; 

        }

        
        public static void Main(String[] args)
        {
           //Program a = new Program(5, 7, 21);
          

           //int auxiliar = a.addInt(5 , 7, 21); // to load static aspect library
         Thread.Sleep(5);
           int auxiliar = 0;
           Program a = new Program(5, 7, 21);
          //File.Copy(@"..\xml\test_all_methodExecuteD_general.xml", @".\test_all_methodExecuteD_general.xml");
          //Thread.Sleep(2000);
           auxiliar = a.addInt(5 , 7, 21); // to load static aspect library
           a.addIntVoid(5, 7, 21);
           DateTime now = DateTime.Now, end = DateTime.Now; ;
          TimeSpan totalDuration = now - now;
           int iterations = 10;
           int j = 0;
           for (; j < iterations; j++)
           {
               now = DateTime.Now;
               for (long i = 0; i <= 10000000; i++)
               {
                   auxiliar = a.addInt(5 + j, 7 + j, 21 + j);
                   //a.addIntVoid(5, 7, 21);
                   //Program aux = new Program(5, 7, 21);
                   //auxiliar = a.addInt(5, 7, 21);

               }

               end = DateTime.Now;
               totalDuration = totalDuration + (end - now);

           }
          
           System.Console.WriteLine("The media duration is {0} ", totalDuration.TotalMilliseconds / iterations);    
      
           auxiliar = a.testAddInt(5 + j, 7 + j, 21 + j);
           //a.testAddIntVoid(5, 7, 21);
        
           //File.Delete(@".\test_all_methodExecuteD_general.xml");
           
           //System.Console.WriteLine("Peak Memory ---- {0} ", WMILibraryNET40.Memory.GetPeakMemoryUsage("Test1Method.exe"));
           
          
            return;
        }

    }
}
