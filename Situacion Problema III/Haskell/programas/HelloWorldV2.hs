module Main (main) where          -- not needed in interpreter, is the default in a module file

main :: IO ()                     -- the compiler can infer this type definition
main = putStrLn "Hello, World!"