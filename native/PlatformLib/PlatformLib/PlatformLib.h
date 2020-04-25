//
//  PlatformLib.h
//  PlatformLib
//
//  Created by Daniel Eke on 2019. 01. 08..
//  Copyright © 2019. Sample. All rights reserved.
//

#import <Foundation/Foundation.h>

FOUNDATION_EXPORT double PlatformLibVersionNumber;
FOUNDATION_EXPORT const unsigned char PlatformLibVersionString[];

@interface PlatformLib : NSObject

- (NSString*)test;

@end



// In this header, you should import all the public headers of your framework using statements like #import <PlatformLib/PublicHeader.h>


