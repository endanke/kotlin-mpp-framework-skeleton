//
//  ViewController.swift
//  KotlinIOS
//
//  Created by Sample on 26.09.18.
//  Copyright © 2018 Sample. All rights reserved.
//

import UIKit
import ProjectName

class ViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let sample1 = FrameworkSample()
        
        print("Sample1 \(sample1.getTime())")
        
        let sample2 = IOSSpecific()
        
        print("Sample2 \(sample2.sayHello())")
    }
    
}
