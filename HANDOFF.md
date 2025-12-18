# Handoff: C++ Support in Javac

## Goal
Modify the OpenJDK Java compiler (`javac`) to accept C/C++ syntax, allowing existing C++ code to be recompiled for the JVM with minimal effort.

## User Requirements
1.  **Proof of Concept**: Focus on demonstrating feasibility with key features.
2.  **Input**: `javac` should accept `.cpp` files directly.
3.  **Features**:
    *   Accept `.cpp` extension.
    *   Handle standard C/C++ syntax (e.g., `void main()`, `#include`, pointers, `struct`).
    *   Port/Mapping of standard libraries (`std::cout`, `printf`).
4.  **Sister Project**: User is also working on porting Java syntax/libs to C++.

## Technical Strategy (PoC)
1.  **File Extension**: Modify the compiler to recognize `.cpp` files as valid source units.
2.  **Global Scope**: Since Java requires all code to be in classes, C++ global functions (like `main`) will be implicitly wrapped in a synthetic class (e.g., `class Global { ... }`).
3.  **Preprocessor**: Implement a basic handler for `#include` to either read files or ignore for the PoC.
4.  **Syntax Mapping**:
    *   `struct` -> `class`
    *   `void main()` -> `public static void main(String[] args)`
    *   `std::cout << "foo"` -> `System.out.print("foo")`
5.  **Pointers**: For the PoC, pointers may be approximated or restricted.

## Current Status
*   **Session**: Initial planning complete.
*   **Next Steps**:
    1.  Locate file extension handling in `jdk.compiler`.
    2.  Modify parser to handle basic C++ constructs.
    3.  Verify with a "Hello World" C++ file.
